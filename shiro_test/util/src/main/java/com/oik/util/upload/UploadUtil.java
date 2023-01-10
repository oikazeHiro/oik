package com.oik.util.upload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 15093
 * @description TODO
 * @date 2023/1/9 10:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadUtil {
    private int pointer;
    private int chunks;
    private String name;
    private String path;


}
