import {createApp} from 'vue'
import App from './App.vue'
import router from './router' //路由
import ElementPlus from 'element-plus' //element-plus
import 'element-plus/dist/index.css' //element-plus 样式
import {createPinia} from 'pinia' // pinia
import * as ElIcons from '@element-plus/icons-vue' //element-plus 图标

const app = createApp(App)
app.use(router)
app.use(ElementPlus)
app.use(createPinia())
for (const name in ElIcons) {
    app.component(name, (ElIcons as any)[name])
}
app.mount('#app')
