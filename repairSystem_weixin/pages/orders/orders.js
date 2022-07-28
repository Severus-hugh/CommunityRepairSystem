// pages/orders/orders.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    currentIndexNav:0,
    orderList:[
      "已提交",
      "已派单",
      "维修中",
      "已完成",
      "全部订单"
    ],
    orderCount:0,
    commitedOrder:[],
    residentId : 10000007,
    
  },
  activeNav(e){
    console.log(e);
    var that = this;
    var a = e.target.dataset.index + 1; 
    this.setData({
      currentIndexNav:e.target.dataset.index
    });
    if(a == 5){
      wx.request({
        url: 'http://localhost:9090/orders/find',
        method: "GET",
        header: {
          'content-type': 'application/json'
        },
        success(res){
          that.setData({
            commitedOrder : res.data,
            orderCount : res.data.length
          })
          console.log(res);
        },
        fail(res){
          console.log(res);
        }
      })
    }else{
      wx.request({
        url: 'http://localhost:9090/orders/findcommited',
        method: "GET",
        data:{
          currentStatus: a,
          residentId : 10000007
        },
        header: {
          'content-type': 'application/json'
        },
        success(res){
          that.setData({
            commitedOrder : res.data,
            orderCount : res.data.length
          })
          console.log(res);
        },
        fail(res){
          console.log(res);
        }
      })
    }
    
    console.log(a);
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})