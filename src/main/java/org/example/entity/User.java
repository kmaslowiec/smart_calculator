package org.example.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DatabaseTable(tableName = "users")
public class User {

    @DatabaseField(generatedId = true, unique = true)
    public Long id;

    @DatabaseField(unique = true, canBeNull = false)
    String name;

    @DatabaseField(canBeNull = false)
    String password;

    @DatabaseField(unique = true, canBeNull = false)
    String email;

    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    byte[] picture;
}