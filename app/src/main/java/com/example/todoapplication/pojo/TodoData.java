package com.example.todoapplication.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/* ------------------------------------------------------------- *
 * Pojo Class/Entity for data TodoData with lombok & Room data base
 * ------------------------------------------------------------- */
@Data
@Entity(tableName = "todo")
public class TodoData {

    /* ------------------------------------------------------------- *
     * Constructor declaration
     * ------------------------------------------------------------- */

    @NonNull
    @PrimaryKey
    @Getter
    @Setter
    @ColumnInfo(name = "id")
    private String id;

    /* ------------------------------------------------------------- *
     * Variable declaration and getter and setter by using lombok
     * and database column name
     * ------------------------------------------------------------- */
    @NonNull
    @Getter
    @Setter
    @ColumnInfo(name = "title")
    private String title;
    @NonNull
    @Getter
    @Setter
    @ColumnInfo(name = "description")
    private String description;
    @Getter
    @Setter
    private int isSelected;

    public TodoData(@NonNull String id, @NonNull String title, @NonNull String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
