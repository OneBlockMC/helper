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

package me.lucko.helper.mongo;

import org.bukkit.configuration.ConfigurationSection;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Represents the credentials for a remote database.
 */
public final class MongoDatabaseCredentials {

    @Nonnull
    public static MongoDatabaseCredentials of(@Nonnull String uri) {
        return new MongoDatabaseCredentials(uri);
    }

    @Nonnull
    public static MongoDatabaseCredentials fromConfig(@Nonnull ConfigurationSection config) {
        return of(
                config.getString("uri", "localhost")
        );
    }

    private final String uri;

    private MongoDatabaseCredentials(@Nonnull String uri) {
        this.uri = Objects.requireNonNull(uri);
    }

    @Nonnull
    public String getUri() {
        return this.uri;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof MongoDatabaseCredentials)) return false;
        final MongoDatabaseCredentials other = (MongoDatabaseCredentials) o;
        return this.getUri().equals(other.getUri());
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getUri().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MongoDatabaseCredentials(" +
                "uri=" + this.getUri() + ")";
    }
}
