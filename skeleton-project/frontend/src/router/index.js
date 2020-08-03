import Vue from "vue";
import Router from "vue-router";

import constants from "../lib/constants";

// 유저
// import Login from "../page/user/Login.vue";
import Join from "../page/user/Join.vue";
import Update from "../page/user/Update.vue";

// 포스트
import List from "../page/post/List.vue";

// PageNotFound
import PageNotFound from "../page/post/PageNotFound.vue";

// NoLoginHome
import NoLoginHome from "../page/main/NoLoginHome.vue";
// LoginHome
import LoginHome from "../page/main/LoginHome.vue";
// FindUserByEmail
import FindUserByEmail from "../page/user/FindUserByEmail.vue";
// ManagePortfolio
import ManagePortfolio from "../page/post/ManagePortfolio.vue";
// ManageExperience
import ManageExperience from "../page/post/ManageExperience.vue";
// PortfolioDetails
import PortfolioDetails from "../page/post/PortfolioDetails.vue";

Vue.use(Router);

export default new Router({
  routes: [
    // 로그인/가입
    // {
    //   path: "/user/login",
    //   name: constants.URL_TYPE.USER.LOGIN,
    //   component: Login,
    // },
    {
      path: "/user/join",
      name: constants.URL_TYPE.USER.JOIN,
      component: Join,
    },
    // 포스트
    {
      path: "/post/list",
      name: constants.URL_TYPE.POST.LIST,
      component: List,
    },
    // PageNotFound
    {
      path: "*",
      name: PageNotFound,
      component: PageNotFound,
    },
    // 회원정보수정
    {
      path: "/user/update",
      name: constants.URL_TYPE.USER.UPDATE,
      component: Update,
    },
    // 비로그인 메인화면
    {
      path: "/",
      name: constants.URL_TYPE.MAIN.NOLOGINHOME,
      component: NoLoginHome,
    },
    // 로그인 메인화면
    {
      path: "/logedin",
      name: constants.URL_TYPE.MAIN.LOGINHOME,
      component: LoginHome,
    },
    // 유저찾기
    {
      path: "/FindUserByEmail/",
      name: constants.URL_TYPE.USER.FINDUSERBYEMAIL,
      component: FindUserByEmail,
      props: true,
    },
    // 포트폴리오관리
    {
      path: "/ManagePortfolio",
      name: constants.URL_TYPE.POST.MANAGEPORTFOLIO,
      component: ManagePortfolio,
    },
    // 경험관리
    {
      path: "/ManageExperience",
      name: constants.URL_TYPE.POST.MANAGEEXPERIENCE,
      component: ManageExperience,
    },
    // 포트폴리오 세부정보
    {
      path: "/PortfolioDetails",
      name: constants.URL_TYPE.POST.PORTFOLIODETAILS,
      component: PortfolioDetails,
      props: true,
    },
  ],
});