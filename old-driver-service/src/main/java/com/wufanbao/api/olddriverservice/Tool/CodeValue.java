package com.wufanbao.api.olddriverservice.Tool;

public enum CodeValue {
    success("成功", 1),
    data("数据为空", 2),
    parameter("参数为空", 3),
    digital("数字签名验证错误", 4),
    employeeLogin("用户名或密码错误", 2001),
    employeeLogin1("您的账号已被禁用", 2002),
    employeeLogin2("您的账号未授权登录本系统", 2003),
    distributionInfo("您暂时没有配送单", 2011),
    beganDelivery("您还有未完成的订单", 2021),
    beganDelivery1("出发失败", 2022),
    terminationDistribution("终止订单失败", 2031),
    confirmDeliveryOrder("确认订单失败", 2041),
    confirmAnOrder("确认订单失败", 2051),
    historicalShippingOrderInfo("您暂时没有历史订单", 2061),
    getDistributionOrderByEmployeeId("您暂时没有配送单", 2071),
    cargoSortingDiagram("没有填仓图信息", 2081),
    getIdealDrawing("理想填仓图生成失败", 2091),
    sss("填仓图位置更新失败", 2101),
    distributionLine("获取配送单信息失败", 2201),
    actualQuantity("获取配送单信息失败", 2211),
    supplementDetailsInfo("配送单id为空", 2221),
    supplementDetailsInfo1("补货单信息为空", 2222),
    supplementOrderhou("获取后仓数据出错", 2231),
    supplementOrderhou1("获取后仓数据为空", 2232),
    supplementOrderDetails("填前仓出错", 2241),
    openStatus("开仓状态出错", 2251),
    updateSupplement("下货出错", 2261),
    goodsUnderList("请求补货单id异常", 2271),
    goodsUnderList1("下货详情为空", 2272),
    openBeforeStorehouse("开仓出错", 2281),
    distributionCompletionAll("补货单详情为空", 2291),
    supplementOrderAfter("后仓确定失败", 2301),
    supplementOrderSure("确认前仓失败", 2311),
    employeeLogins("注销成功", 2321),
    employeeLogins1("失败", 2322),
    rollbackStatus("回滚失败", 2331),
    distributionStatus("确认配送单失败", 2341),
    machineTable("机器表格数据异常", 2351),
    machineOpenStatus("请扫描机器二维码", 2352),
    scanCode("该机器没有下货操作的补货单", 2361);

    CodeValue(String message, int code) {
        this.message = message;
        this.code = code;
    }

    private String message;
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
