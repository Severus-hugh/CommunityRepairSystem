// pages/repairOrder/repairOrder.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    index: 0,
    holidayReason:"",
    holidayTime:"2022-05-19",
    newOrdersList:[],

  },
  changeTabbar(e){
    this.setData({ index: e.currentTarget.dataset.id})
  },
  changeToRecord(){
    wx.navigateTo({
      url: '/pages/holiday/holiday',
    })
  },
  commit:function(){
    wx.request({
      url: 'http://localhost:9090/holiday/save',
      method:'POST',
      // header: { 
      //   "Content-Type": "application/x-www-form-urlencoded" //POST方式
      // },
      header: {
        'content-type': 'application/json'
      },
      data:{
        repairmanId:81030001,
        holidayTime:this.data.holidayTime,
        holidayReason:this.data.holidayReason
      },
      success(res){
        console.log("请假成功",res);
        wx.showToast({
          title: '请假成功',
          icon: 'success',
          duration: 2000//持续的时间
        });
      },fail(res){
        console.log("请假失败",res);
      }
    });
    console.log("commit",this.data.holidayTime);
    console.log("commit",this.data.holidayReason);
    this.reset();
  },
  reset:function(){
    this.setData({
      holidayTime:"2022-05-19",
      holidayReason:""
    });
  },
  setHolidayReason:function(e){
    this.setData({
      holidayReason:e.detail.value
    })
    
  },
  setHolidayTime:function(e){
    this.setData({
      holidayTime:e.detail.value
    })
  },
  load:function(){
    var that = this;
    //查询刚被派单的订单，状态为2
    wx.request({
      url: 'http://localhost:9090/orders/findcommited',
      method: "GET",
        data:{
          currentStatus: 2,
          residentId : 10000007
        },
        header: {
          'content-type': 'application/json'
        },
        success(res){
          that.setData({
            newOrdersList : res.data,
          });
          console.log("获取新订单成功",res);
        },
        fail(res){
          console.log("获取新订单失败",res);
        }
    })
  },
  accept:function(e){
    console.log(e);
    var a = e.currentTarget.dataset.id;
    var b = this.data.newOrdersList[a].orderId;
    console.log(b);
    wx.request({
      url: 'http://localhost:9090/orders/update',
      method:"POST",
      header: {
        'content-type': 'application/json'
      },
      data:{
        currentStatus:3,
        orderId:b,
      },
      success(res){
        console.log("接单成功",res);
        wx.showToast({
          title: '接单成功',
          icon: 'success',
          duration: 2000//持续的时间
        })
      },
      fail(res){
        console.log("接单失败",res);
      }
    });
    this.load();
  },
  refuse:function(e){
    console.log(e);
    var c = e.currentTarget.dataset.id;
    var d = this.data.newOrdersList[c].orderId;
    console.log(d);
    wx.request({
      url: 'http://localhost:9090/orders/update',
      method:"POST",
      header: {
        'content-type': 'application/json'
      },
      data:{
        currentStatus:1,
        orderId:d,
        repairmanId:null,//空值好像写不回去
      },
      success(res){
        console.log("拒单成功",res);
        wx.showToast({
          title: '拒单成功',
          icon: 'success',
          duration: 2000//持续的时间
        })
      },
      fail(res){
        console.log("拒单失败",res);
      }
    });
    this.load();
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
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