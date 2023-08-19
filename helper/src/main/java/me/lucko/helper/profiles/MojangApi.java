/*
 * This file is part of helper, licensed under the MIT License.
 *
 *  Copyright (c) lucko (Luck) <luck@lucko.me>
 *  Copyright (c) contributors
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package me.lucko.helper.profiles;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import me.lucko.helper.promise.Promise;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;
import java.util.Optional;
import java.util.UUID;

/**
 * Utilities for interacting with the Mojang API.
 */
public final class MojangApi {
    public static final String PROFILE_HISTORY_URL = "https://sessionserver.mojang.com/session/minecraft/profile/{0}";

    /**
     * Gets a promise of the history of the names owned by the player with the supplied {@link UUID}, in chronological order, fetching it from the Mojang API.
     * The Mojang API has a limit of 600 requests per 10 minutes.
     *
     * @param uuid the {@link UUID} of the player from which to fetch the name history
     * @return a promise of a List String with all the names owned by the player with the supplied uuid, in chronological order
     */
//    public static Promise<List<String>> getUsernameHistory(UUID uuid) {
//        Objects.requireNonNull(uuid, "uuid");
//
//        return Schedulers.async().supply(() -> {
//            List<String> names = Lists.newArrayList();
//
//            try (JsonReader reader = new JsonReader(new InputStreamReader(new URL(String.format(NAME_HISTORY_URL, UndashedUuids.toString(uuid))).openConnection().getInputStream()))) {
//                reader.beginArray();
//                for (int i = 0; reader.hasNext(); i++) {
//                    reader.beginObject();
//                    reader.skipValue();
//                    names.add(reader.nextString());
//                    if (i != 0) {
//                        reader.skipValue();
//                        reader.skipValue();
//                    }
//                    reader.endObject();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return names;
//        });
//    }

    public static Promise<Optional<String>> uuidToUsername(UUID uuid) {
        String url = MessageFormat.format(PROFILE_HISTORY_URL, uuid.toString());

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonElement element = new JsonParser().parse(response.body());

            System.out.println(element.toString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return Promise.completed(Optional.empty());
    }


    private MojangApi() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

}
