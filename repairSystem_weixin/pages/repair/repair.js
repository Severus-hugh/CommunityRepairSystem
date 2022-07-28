// pages/repair/repair.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    wxNickname: "李",
    person:"",
    address:"",
    reptime:"",
    tel:"",
    question:"",
    quesId:"",
    communityList:[],


    // 下拉框相关
    tabType: 'tab1',
		key: 'tab1',
		conditionList: [{
				title: '水电类',
				id: '2001',
				select: true
			},
			{
				title: '家用电器',
				id: '2002',
				select: false
			},
			{
				title: '暖气',
				id: '2003',
				select: false
			},
			{
				title: '天然气',
				id: '2004',
				select: false
			},
			{
				title: '开锁',
				id: '2005',
				select: false
			},
			{
				title: '电梯',
				id: '2006',
				select: false
			},
			{
				title: '下水管道',
				id: '2007',
				select: false
			},
			{
				title: '墙体装修',
				id: '2008',
				select: false
			},
			{
				title: '其他',
				id: '2009',
				select: false
			}
		],
		choosedCondition: {
			title: '水电类',
			id: '2001'
		},
    conditionVisible: false,
    choosedCommunity:{
      title:'凤南小区',
      id:'12210000'
    },
    communityList:[],
    // communityList:[{
    //   title: '凤南小区',
    //   id: '12210000',
    //   select: true
    // },
    // {
    //   title: '湖滨小区（南区）',
    //   id: '12220001',
    //   select: false
    // }],
    communityVisible:false,
  },

  commitPerson:function(e){
    this.setData({
      person:e.detail.value
    });
    console.log(e);
  },//将文本框数据写入参数，然后就可以通过request传参，前台的函数类型为bindinput
  commitAddress:function(e){
    this.setData({
      address:e.detail.value
    });
  },
  commitReptime:function(e){
    this.setData({
      reptime:e.detail.value
    });
  },
  commitTel:function(e){
    this.setData({
      tel:e.detail.value
    });
  },
  commitQues:function(e){
    this.setData({
      question:e.detail.value
    });
  },


  commit:function(e){
    var that = this;
    console.log("点击提交",e);
    wx.request({
      url: 'http://localhost:9090/orders/neworderone',
      method:"POST",
      header: {
        'content-type': 'application/json'
      },
      data:{
          // person:this.person,
          // community:this.choosedCommunity.id,
          // address:this.address,
          // reptime:this.reptime,
          // tel:this.tel,
          // question:this.question,
          // quesId:this.choosedCondition.id,
          residentId:10000007,
          orderCommunityId:that.data.choosedCommunity.id,
          orderAddress:that.data.address,
          questionId:that.data.choosedCondition.id,
          suitableTime:that.data.reptime,
          contactTel:that.data.tel,
          orderDescription:that.data.question
      },
      success(res){
        console.log("订单创建成功,返回数据是：",res);
        wx.showToast({
          title: '订单提交成功',
          icon: 'success',
          duration: 2000//持续的时间
        })
      },fail(res){
        console.log("订单创建失败");
      }
    });
    this.reset();
  },
  
// 下拉框相关
showCondition() {
  this.setData({
    conditionVisible: !this.data.conditionVisible
  })
},
// 改变查询项
onChnageCondition(e) {
  const list = this.data.conditionList
  list.forEach(item => {
    if (item.id === e.currentTarget.dataset.id) {
      item.select = true
      this.setData({
        'choosedCondition.title': item.title,
        'choosedCondition.id': item.id
      })
    } else {
      item.select = false
    }
  })
  // this.setData({
  //   communityList: list
  // })
},
// 下拉框结束
showCommunity() {
  this.setData({
    communityVisible: !this.data.communityVisible
  })
},
onChnageCommunity(e){ 
  console.log("onChnageCommunity",e);
  this.data.communityList.forEach(item => {
    if (item.communityId === e.currentTarget.dataset.id) {
      item.select = true
      this.setData({
        'choosedCommunity.title': item.communityName,
        'choosedCommunity.id': item.communityId
      })
    } else {
      item.select = false
    }
  })
  // this.setData({
  //   conditionList: list
  // })
},
  reset:function(){
    this.setData({
      person : "",
      address : "",
      reptime : "",
      tel : "",
      question : "",
      choosedCondition: {
        title: '水电类',
        id: '2001'
      },
      choosedCommunity:{
        title:'凤南小区',
        id:'12210000'
      },
    });
    
  },
  loadCommunity:function(){
  //初次加载更新小区列表
  var that = this;
  wx.request({
    url: 'http://localhost:9090/community/find',
    method:"GET",
    header: {
      'content-type': 'application/json'
    },
    success(res){
      that.setData({
        communityList : res.data,
      }),
     
      console.log("获取小区名",res);
    },fail(res){
      console.log("获取小区失败");
    }
  });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.loadCommunity();
    console.log("communityList:",this.communityList);
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