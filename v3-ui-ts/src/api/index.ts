import axios, {AxiosError, AxiosInstance, AxiosRequestConfig, AxiosResponse,} from 'axios'
import {ElNotification} from 'element-plus'
import router from '@/router'
import qs from 'qs'
import {query} from "@/entity/interface";

// 数据返回的接口
// 定义请求响应参数，不含data
interface Result {
    code: number
    msg: string
}

// 请求响应参数，包含data
interface ResultData<T = any> extends Result {
  data?: T
  count?: number
}

const URL = ''

enum RequestEnums {
  TIMEOUT = 20000,
  OVERDUE = 401, // 登录失效
  FAIL = 400, // 请求失败
  SUCCESS = 200, // 请求成功
  NOTFOUND = 404,
  permsError = 407,
}

const config = {
  // 默认地址
  baseURL: URL as string,
  // 设置超时时间
  timeout: RequestEnums.TIMEOUT as number,
  // 跨域时候允许携带凭证
  withCredentials: true,
}

class RequestHttp {
  // 定义成员变量并指定类型
  service: AxiosInstance

  public constructor(config: AxiosRequestConfig) {
    // 实例化axios
    this.service = axios.create(config)

    /**
     * 请求拦截器
     * 客户端发送请求 -> [请求拦截器] -> 服务器
     */
    this.service.interceptors.request.use(
        (config: AxiosRequestConfig) => {
          const token = localStorage.getItem('token') || ''
          return {
            ...config,
            headers: {
              'Content-Type': 'application/json',
              Accept: 'application/json',
              Authorization: token,
            },
          }
        },
        (error: AxiosError) => {
          // 请求报错
          Promise.reject(error)
        },
    )

    /**
     * 响应拦截器
     * 服务器换返回信息 -> [拦截统一处理] -> 客户端JS获取到信息
     */
    this.service.interceptors.response.use(
        (response: AxiosResponse) => {
            const {data, config} = response // 解构
            debugger
            console.log(data.code)
            if (data.code == RequestEnums.OVERDUE) {
                // 登录信息失效，应跳转到登录页面，并清空本地的token
                localStorage.setItem('token', '')
                router.replace({
                    path: '/login',
                })
                return Promise.reject(data)
            }
            if (data.code == RequestEnums.permsError) {
                ElNotification.warning(data.msg)
                return Promise.reject(data)
            }
            // 全局错误信息拦截（防止下载文件得时候返回数据流，没有code，直接报错）
            if (!data.code) {
                //   ElMessage.error(data) // 此处也可以使用组件提示报错信息
                ElNotification.error(data)
                return Promise.reject(data)
            }
            // eslint-disable-next-line no-empty
            if (response.headers.Authorization != null) {
                localStorage.setItem('token', response.headers.Authorization)
            }
          if (data.msg != null && data.msg != ''){
              ElNotification.success(data.msg)
          }
          return data
        },
        (error: AxiosError) => {
          const {response} = error
          if (response) {
            this.handleCode(response.status)
          }
          if (!window.navigator.onLine) {
            //   ElMessage.error('网络连接失败')
            ElNotification.error('网络连接失败')
            // 可以跳转到错误页面，也可以不做操作
            return router.replace({
              path: '/404',
            })
          }
        },
    )
  }

  handleCode(code: number): void {
    switch (code) {
      case 401:
        // ElMessage.error('登录失败，请重新登录')
        ElNotification.error('登录失败，请重新登录')
        localStorage.setItem('token', '')
        router.replace({
          path: '/login',
        })
        break
      default:
        // ElMessage.error('请求失败')
        ElNotification.error('请求失败')
        break
    }
  }


    // 常用方法封装
  get<T>(url: string, params?: object): Promise<ResultData<T>> {
      const json = qs.stringify(params, {indices: false})
      return this.service.get(url, {params})
  }

  post<T>(url: string, params?: object): Promise<ResultData<T>> {
    // console.log(URL)
      return this.service.post(url, params)
  }

    put<T>(url: string, params?: object): Promise<ResultData<T>> {
        return this.service.put(url, params)
    }

    delete<T>(url: string, params?: object): Promise<ResultData<T>> {
        return this.service.delete(url, {params})
    }

    get2<T>(url: string, params?: query<T>): Promise<ResultData<T>> {
        params.page.records = []
        const page = qs.stringify(params.page, {allowDots: true})
        const param = qs.stringify(params.param, {allowDots: true})
        return this.service.get(url + "?" + page + '&' + param)
    }

}

// 导出一个实例对象
export default new RequestHttp(config)
