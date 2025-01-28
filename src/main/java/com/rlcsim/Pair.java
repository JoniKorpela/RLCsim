package com.rlcsim;

import java.io.Serializable;
import java.util.Objects;

public class Pair<K, V> implements Serializable {

    public K k;
    public V v;

    public Pair(K k, V v) {
        this.k = k;
        this.v = v;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Pair)) {
            return false;
        }

        @SuppressWarnings("unchecked")
        Pair<K, V> other = (Pair<K, V>) obj;

        return Objects.equals(k, other.k) && Objects.equals(v, other.v);
    }

    @Override
    public int hashCode() {
        return Objects.hash(k, v);
    }

    public K getFirst() {
        return k;
    }

    public V getSecond() {
        return v;
    }

}
