import {App, Component} from "vue"
import OikIconButton from "@/components/button/OikIconButton.vue";
import TimeComponents from "@/components/table/TimeComponents.vue";
import DictComponents from "@/components/table/DictComponents.vue";

//定义接口，其中有一个install的方法
interface GlobalComponents {
    install: (app: App) => void
}

const componentsArr: Component[] = [
    OikIconButton,
    TimeComponents,
    DictComponents
]

const aiComponents: GlobalComponents = {
    install(app) {
        componentsArr.map((item: Component) => {  //遍历注册全局组件
            app.component(item.name!, item)
        })
    }
}
export default aiComponents
