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
    menuId?: number
    parentId?: number
    menuName?: string
    path?: string
    component?: string
    icon?: string
    type?: string
    orderNum?: number
    createTime?: string
    updateTime?: string
    status?: string
    createUsername?: string
    createUserId?: string
    updateUsername?: string
    updateUserId?: string
    children?: Array<menus>
}

export interface result<T> {
    code: number
    msg: string
    data: T
    count?: number
}

export interface userDto {
    userId: string
    username: string
    deptId: string
    email: string
    mobile: string
    description: string
    token: string
    ssex: string
    avatar: string
    ip: string
}


export interface page<T> {
    records?: Array<T>,
    total: number,
    size: number,
    current: number,
    orders?: {
        column?: string,
        asc?: boolean
    },
    optimizeCountSql?: boolean,
    searchCount?: boolean,
    maxLimit?: number,
    countId?:string
}

export interface query<T> {
    page:page<T>,
    param:T
}

export interface dict {
    dictId?:number,
    keyy?:number,
    valuee?: string,
    fieldName?: string,
    tableName?: string,
    createTime?: string
    createUserId?: number,
    createUsername?: string,
    updateTime?: string,
    updateUsername?: string,
    status?: string,
    sort?: number,
    otherKeyy?: number,
    children?: Array<dict>
}