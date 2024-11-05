package com.election.UserManagement.common;

import lombok.*;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponseV2<T> {
    private String message;
    private ResponseStatusEnum status;
    private T _embedded;
}
