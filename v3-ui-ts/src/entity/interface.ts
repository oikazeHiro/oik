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
    menuId?: string,
    parentId?: string,
    menuName?: string,
    path?: string,
    component?: string,
    perms?: string,
    icon?: string,
    type?: string,
    orderNum?: number,
    createTime?: string | number,
    updateTime?: string | number,
    status?: string,
    createUsername?: string,
    createUserId?: string,
    updateUsername?: string,
    updateUserId?: string,
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

export interface user {
    userId?: string,
    username?: string,
    password?: string,
    deptId?: string,
    email?: string,
    mobile?: string,
    status?: number,
    lastLoginTime?: string | number,
    description?: string,
    avatar?: string
    createUsername?: string,
    createUserId?: string,
    createTime?: string | number,
    updateUsername?: string,
    updateTime?: string | number,
    updateUserId?: string,
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
    dictId?: string,
    keyy?: number,
    valuee?: string,
    fieldName?: string,
    tableName?: string,
    createTime?: string | number
    createUserId?: string,
    createUsername?: string,
    updateTime?: string | number,
    updateUsername?: string,
    status?: string,
    sort?: number,
    otherKeyy?: number,
    children?: Array<dict>
}