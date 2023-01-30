package com.oik.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 15093
 * @description TODO
 * @date 2023/1/30 14:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AddRole {
    private String[] youRole;
    private String userId;
}
