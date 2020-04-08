package org.example.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DatabaseTable(tableName = "histories")
public class History {

    @DatabaseField(generatedId = true, unique = true)
    private long id;

    @DatabaseField
    private Timestamp date;

    @DatabaseField
    private String calculation;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private User user;
}
