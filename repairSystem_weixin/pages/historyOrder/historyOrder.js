// pages/historyOrder/historyOrder.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    index: 0,
    orderList:[],
    orderIds:0,
    orderId:0,
    historyList:[],

  },
  changeTabbar(e){
    this.setData({ index: e.currentTarget.dataset.id})
  },
  /**
   * 生命周期函数--监听页面加载
   */
  load:function(){
    var that = this;
    wx.request({
      url: 'http://localhost:9090/orders/findreporder',
      method:"GET",
      header: {
        'content-type': 'application/json'
      },
      data:{
        currentStatus:3,
        repairmanId:81030001
      },
      success(res){
        that.setData({
          orderList : res.data,
        })
      },
      fail(res){
        console.log("获取维修员已接单列表失败");
      }
    })
  },
  loadAll:function(){
    var that = this;
    wx.request({
      url: 'http://localhost:9090/orders/findreporders',
      method:"GET",
      header: {
        'content-type': 'application/json'
      },
      data:{
        repairmanId:81030001
      },
      success(res){
        that.setData({
          historyList : res.data,
        })
      },
      fail(res){
        console.log("获取维修员所有接单列表失败");
      }
    })
  },
  onLoad(options) {
    this.load();
    this.loadAll();
  },
  accept:function(e){
    var that = this;
    var i = e.currentTarget.dataset.id;
    console.log("accept按钮",e);
    console.log("orderlist[i]",that.data.orderList[i].orderId);
    this.setData({
      orderIds : that.data.orderList[i].orderId
    });
    console.log("orderIds",this.data.orderIds);
    wx.request({
      url: 'http://localhost:9090/orders/repairorder',
      method:"POST",
      header: {
        'content-type': 'application/json'
      },
      data:{
        orderId:this.data.orderIds,
      },
      success(res){
        console.log("修复成功",res);
        wx.showToast({
          title: '修复成功',
          icon: 'success',
          duration: 2000//持续的时间
        });
      }, 
      
      fail(res){
        console.log("修复失败",res);
      }
    });
    this.load();
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
    if (typeof this.getTabBar === 'function' &&
      this.getTabBar()) {
      this.getTabBar().setData({
        selected: 0
      })
    }
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