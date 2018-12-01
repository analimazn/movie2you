/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.http.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicHeaderValueParser;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.ParserCursor;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;

/**
 * A collection of utilities for encoding URLs.
 *
 * @since 4.0
 */
public class URLEncodedUtils {

    public static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
    private static final String PARAMETER_SEPARATOR = "&";
    private static final String NAME_VALUE_SEPARATOR = "=";

    /**
     * Returns a list of {@link NameValuePair NameValuePairs} as built from the
     * URI's query portion. For example, a URI of
     * http://example.org/path/to/file?a=1&b=2&c=3 would return a list of three
     * NameValuePairs, one for a=1, one for b=2, and one for c=3.
     * <p>
     * This is typically useful while parsing an HTTP PUT.
     *
     * @param uri
     *            uri to parse
     * @param encoding
     *            encoding to use while parsing the query
     */
    public static List <NameValuePair> parse (final URI uri, final String encoding) {
        final String query = uri.getRawQuery();
        if (query != null && query.length() > 0) {
            return parse(query, encoding);
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Returns a list of {@link NameValuePair NameValuePairs} as parsed from an
     * {@link HttpEntity}. The encoding is taken from the entity's
     * Content-Encoding header.
     * <p>
     * This is typically used while parsing an HTTP POST.
     *
     * @param entity
     *            The entity to parse
     * @throws IOException
     *             If there was an exception getting the entity's data.
     */
    public static List <NameValuePair> parse (
            final HttpEntity entity) throws IOException {
        List <NameValuePair> result = Collections.emptyList();

        String contentType = null;
        String charset = null;

        Header h = entity.getContentType();
        if (h != null) {
            HeaderElement[] elems = h.getElements();
            if (elems.length > 0) {
                HeaderElement elem = elems[0];
                contentType = elem.getName();
                NameValuePair param = elem.getParameterByName("charset");
                if (param != null) {
                    charset = param.getValue();
                }
            }
        }

        if (contentType != null && contentType.equalsIgnoreCase(CONTENT_TYPE)) {
            final String content = EntityUtils.toString(entity, HTTP.ASCII);
            if (content != null && content.length() > 0) {
                result = new ArrayList <NameValuePair>();
                parse(result, new Scanner(content), charset);
            }
        }
        return result;
    }

    /**
     * Returns true if the entity's Content-Type header is
     * <code>application/x-www-form-urlencoded</code>.
     */
    public static boolean isEncoded (final HttpEntity entity) {
        Header h = entity.getContentType();
        if (h != null) {
            HeaderElement[] elems = h.getElements();
            if (elems.length > 0) {
                String contentType = elems[0].getName();
                return contentType.equalsIgnoreCase(CONTENT_TYPE);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Adds all parameters within the Scanner to the list of
     * <code>parameters</code>, as encoded by <code>encoding</code>. For
     * example, a scanner containing the string <code>a=1&b=2&c=3</code> would
     * add the {@link NameValuePair NameValuePairs} a=1, b=2, and c=3 to the
     * list of parameters.
     *
     * @param parameters
     *            List to add parameters to.
     * @param scanner
     *            Input that contains the parameters to parse.
     * @param encoding
     *            Encoding to use when decoding the parameters.
     *
     * @deprecated use {@link #parse(String, String)}
     */
    @Deprecated
    public static void parse (
            final List <NameValuePair> parameters,
            final Scanner scanner,
            final String encoding) {
        scanner.useDelimiter(PARAMETER_SEPARATOR);
        while (scanner.hasNext()) {
            final String[] nameValue = scanner.next().split(NAME_VALUE_SEPARATOR);
            if (nameValue.length == 0 || nameValue.length > 2)
                throw new IllegalArgumentException("bad parameter");

            final String name = decode(nameValue[0], encoding);
            String value = null;
            if (nameValue.length == 2)
                value = decode(nameValue[1], encoding);
            parameters.add(new BasicNameValuePair(name, value));
        }
    }

    private static final char[] DELIM = new char[] { '&' };

    /**
     * Returns a list of {@link NameValuePair NameValuePairs} as parsed from the given string
     * using the given character encoding.
     *
     * @param s
     *            text to parse.
     * @param encoding
     *            Encoding to use when decoding the parameters.
     *
     * @since 4.2
     */
    public static List<NameValuePair> parse (final String s, final String encoding) {
        if (s == null) {
            return Collections.emptyList();
        }
        BasicHeaderValueParser parser = BasicHeaderValueParser.DEFAULT;
        CharArrayBuffer buffer = new CharArrayBuffer(s.length());
        buffer.append(s);
        ParserCursor cursor = new ParserCursor(0, buffer.length());
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        while (!cursor.atEnd()) {
            NameValuePair nvp = parser.parseNameValuePair(buffer, cursor, DELIM);
            if (nvp.getName().length() > 0) {
                list.add(new BasicNameValuePair(
                        decode(nvp.getName(), encoding),
                        decode(nvp.getValue(), encoding)));
            }
        }
        return list;
    }

    /**
     * Returns a String that is suitable for use as an <code>application/x-www-form-urlencoded</code>
     * list of parameters in an HTTP PUT or HTTP POST.
     *
     * @param parameters  The parameters to include.
     * @param encoding The encoding to use.
     */
    public static String format (
            final List <? extends NameValuePair> parameters,
            final String encoding) {
        final StringBuilder result = new StringBuilder();
        for (final NameValuePair parameter : parameters) {
            final String encodedName = encode(parameter.getName(), encoding);
            final String encodedValue = encode(parameter.getValue(), encoding);
            if (result.length() > 0) {
                result.append(PARAMETER_SEPARATOR);
            }
            result.append(encodedName);
            if (encodedValue != null) {
                result.append(NAME_VALUE_SEPARATOR);
                result.append(encodedValue);
            }
        }
        return result.toString();
    }

    /**
     * Returns a String that is suitable for use as an <code>application/x-www-form-urlencoded</code>
     * list of parameters in an HTTP PUT or HTTP POST.
     *
     * @param parameters  The parameters to include.
     * @param encoding The encoding to use.
     *
     * @since 4.2
     */
    public static String format (
            final Iterable<? extends NameValuePair> parameters,
            final String encoding) {
        final StringBuilder result = new StringBuilder();
        for (final NameValuePair parameter : parameters) {
            final String encodedName = encode(parameter.getName(), encoding);
            final String encodedValue = encode(parameter.getValue(), encoding);
            if (result.length() > 0) {
                result.append(PARAMETER_SEPARATOR);
            }
            result.append(encodedName);
            if (encodedValue != null) {
                result.append(NAME_VALUE_SEPARATOR);
                result.append(encodedValue);
            }
        }
        return result.toString();
    }

    private static String decode (final String content, final String encoding) {
        if (content == null) {
            return null;
        }
        try {
            return URLDecoder.decode(content,
                    encoding != null ? encoding : HTTP.DEFAULT_CONTENT_CHARSET);
        } catch (UnsupportedEncodingException problem) {
            throw new IllegalArgumentException(problem);
        }
    }

    private static String encode (final String content, final String encoding) {
        if (content == null) {
            return null;
        }
        try {
            return URLEncoder.encode(content,
                    encoding != null ? encoding : HTTP.DEFAULT_CONTENT_CHARSET);
        } catch (UnsupportedEncodingException problem) {
            throw new IllegalArgumentException(problem);
        }
    }

}