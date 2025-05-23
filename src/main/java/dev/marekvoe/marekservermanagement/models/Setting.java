package dev.marekvoe.marekservermanagement.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Setting {
    @Id
    @Column(name = "`key`")
    private String key;
    private String value;

    public Setting() {}

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public Setting(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
