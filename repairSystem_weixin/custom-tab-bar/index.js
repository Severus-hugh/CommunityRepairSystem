// pages/custom-tab-bar/index.js
Component({
  data: {
    active:0,
    color:"#000000",
    role:1,
    selectedColor:"#ff9e30",
    list:[],
    allList:[{
      list1:
      [{
        "pagePath": "pages/indexs/indexs",
        "text": "我要报修",
        "iconPath": "/images/repair.png",
        "selectedIconPath": "/images/repairSelected.png"
      },
      {
        "pagePath": "pages/orders/orders",
        "text": "报修订单",
        "iconPath": "/images/order.png",
        "selectedIconPath": "/images/orderSelected.png"
      },
      {
        "pagePath": "pages/mine/mine",
        "text": "个人中心",
        "iconPath": "/images/mine.png",
        "selectedIconPath": "/images/mineSelected.png"
      }],
      list2:
      [{
        "pagePath": "pages/repairOrder/repairOrder",
        "text": "维修订单",
        "iconPath": "/images/dealingOrders.png",
        "selectedIconPath": "/images/dealingOrdersSelected.png"
      },
      {
        "pagePath": "pages/historyOrder/historyOrder",
        "text": "历史订单",
        "iconPath": "/images/finishOrderSelected.png",
        "selectedIconPath": "/images/finishOrder.png"
      },
      {
        "pagePath": "pages/mine/mine",
        "text": "个人中心",
        "iconPath": "/images/mine.png",
        "selectedIconPath": "/images/mineSelected.png"
      }]
    }],
  },
  lifetimes:{
    attached(){
      console.log("页面加载 ");
      var role = wx.getStorageSync('roleId');
      if(role == 1){
        this.setData({
          list : this.data.allList[0].list1
        })
        console.log('用户登录');
      }else if(role == 2){
        this.setData({
          list : this.data.allList[0].list2
        })
      }
    },
  },
  
  methods:{
    switchTab(e){
      const data = e.currentTarget.dataset
      const url = data.path
      wx.switchTab({
        url
      })
      this.setData({
        selected:data.index
      })
    }
  }
})