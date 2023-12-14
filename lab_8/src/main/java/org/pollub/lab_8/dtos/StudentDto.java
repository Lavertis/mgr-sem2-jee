package org.pollub.lab_8.dtos;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String street;
    private String city;
    private String zip;
    private String state;
}
