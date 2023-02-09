package com.oik.api.liteflow;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

/**
 * @author 15093
 * @description TODO
 * @date 2023/2/6 9:05
 */
@LiteflowComponent("a")
public class ACmp extends NodeComponent {

    @Override
    public void process() {
        System.out.println("ACmp executed!");
    }
}

