export interface LoginFrom {
    username: string,
    password: string,
    code?: string
}

export class LoginFrom2 {
    username: string;
    password: string;
    code?: string

    constructor(username: string, password: string, code?: string) {
        this.username = username;
        this.password = password;
        this.code = code;
    }
}

export interface menus {
    menuId: number
    parentId: number
    menuName: string
    path: string
    component: string
    icon: string
    type: string
    orderNum: number
    children?: Array<menus>
}

export interface result<T> {
    code: number
    msg: string
    data: T
    count?: number
}
