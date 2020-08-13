<template>
  <div id="nologinHome">
    <!-- <b-container id="nologinHomeContainer">
    <b-row align-v="center">-->
    <b-navbar
      class="nologinMenue ml-auto mr-auto"
      type="dark"
      v-if="!constants.IS_LOGED_IN"
    >
      <b-navbar-nav class="ml-auto mr-auto">
        <b-nav-item class="mr-5" href="#Home">Home</b-nav-item>
        <b-nav-item class="mr-5" href="#AboutUs">About Us</b-nav-item>
        <b-nav-item class="mr-5" href="#Ourservice">Our Service</b-nav-item>
        <b-nav-item class="mr-5" href="#Contact">Contact</b-nav-item>
      </b-navbar-nav>
    </b-navbar>
    <!-- </b-row>
    <b-row align-v="center">-->
    <div id="Home">
      <b-img
        :src="require(`@/assets/img/logo2-2.png`)"
        contain
        width="500"
        id="HomeLogo"
      ></b-img>
      <div style="text-align: center;" class="mt-3">
        <b-button
          class="LoginBtn mr-5"
          @click="$bvModal.show('LoginModal')"
          style="text-align: center; cursor: pointer;"
          >로그인</b-button
        >
        <b-modal id="LoginModal" hide-footer>
          <template v-slot:modal-title>로그인</template>
          <div class="d-block text-center">
            <div class="custom-login-style">
              <b-form-input
                v-model="emailOrUid"
                id="emailOrUid"
                placeholder="이메일 혹은 아이디를 입력해주세요"
                type="text"
                style="width: 60%;"
                class="mt-1 mb-1"
              ></b-form-input>

              <b-form-input
                @keyup.enter="login"
                v-model="password"
                type="password"
                placeholder="영문, 숫자 혼용 8자 이상"
                style="width: 60%;"
                class="mt-1 mb-1"
              ></b-form-input>
              <span class="findUser text-secondary"
                >아이디 또는 비밀번호를 잊으셨나요?</span
              >
            </div>
            <b-button @click="login" class="mt-3">로그인</b-button>
          </div>
        </b-modal>
        <b-button
          class="JoinBtn"
          @click="$bvModal.show('JoinModal')"
          style="text-align: center; cursor: pointer;"
          >회원가입</b-button
        >
        <b-modal id="JoinModal" hide-footer>
          <template v-slot:modal-title>
            <p class="mt-2 mb-2">회원가입</p>
            <small class="mb-2">회원 정보를 입력해주세요.</small>
          </template>
          <div class="d-block text-center">
            <Join @close="close" />
          </div>
        </b-modal>
      </div>
    </div>
    <!-- </b-row>
    <b-row align-v="center">-->

    <div id="AboutUs">
      <div id="AboutUsCarousel">
        <b-carousel
          id="carousel-fade"
          style="text-shadow: 0px 0px 2px #000"
          :interval="4000"
          fade
          indicators
          img-width="1024"
          img-height="480"
        >
          <b-carousel-slide
            caption="활동을 기록해보세요."
            img-src="https://cdn.pixabay.com/photo/2017/07/31/21/59/computer-2561477_1280.jpg"
          ></b-carousel-slide>
          <b-carousel-slide
            caption="프로젝트를 보기 쉽게 정리 해 보세요."
            img-src="https://cdn.pixabay.com/photo/2015/04/20/13/17/work-731198_1280.jpg"
          ></b-carousel-slide>
          <b-carousel-slide
            caption="금세 나만의 포트폴리오가 될 거에요."
            img-src="https://cdn.pixabay.com/photo/2012/02/22/19/38/business-15498_1280.jpg"
          ></b-carousel-slide>
          <b-carousel-slide
            caption="꿈을 향한 당신의 힘찬 첫 걸음을 시작해보세요!"
            img-src="https://cdn.pixabay.com/photo/2018/02/27/06/30/skyscraper-3184798_1280.jpg"
          ></b-carousel-slide>
        </b-carousel>
      </div>
    </div>
    <!-- </b-row>

    <b-row align-v="center">-->
    <div class="BackColor">
      <div id="Ourservice">
        <b-row align-h="center">
          <b-col cols="3">
            <div id="service">
              <b-icon icon="list-ul" font-scale="5" class="m-2"></b-icon>
              <h2 class="mt-2 mb-5">정리</h2>
              <p>프로젝트 및 경험을</p>
              <p>보기 쉽게 정리하고,</p>
            </div>
          </b-col>
          <b-col cols="3">
            <div id="service">
              <b-icon icon="tags-fill" font-scale="5" class="m-2"></b-icon>
              <h2 class="mt-2 mb-5">태그</h2>
              <p>각 요소마다 태그를 달아</p>
              <p>활용성을 높이고,</p>
            </div>
          </b-col>
          <b-col cols="3">
            <div id="service">
              <b-icon icon="check2-square" font-scale="5" class="m-2"></b-icon>
              <h2 class="mt-2 mb-5">활용</h2>
              <p>한 눈에 필요한 자료를</p>
              <p>확인할 수 있습니다.</p>
            </div>
          </b-col>
        </b-row>
      </div>
      <!-- </b-row>
      <b-row align-v="center">-->
      <div id="Contact">
        <h3 class="m-3">Contact for</h3>
        <p>TeamLP@nocontact.here</p>
        <p>123-456-7890</p>
      </div>
      <!-- </b-row>
      </b-container>-->
    </div>

    <!-- <br />
          <div style="text-align: center;">
            <span
              class="findUser text-secondary"
              @click="$bvModal.show('findUserModal')"
              style="text-align: center; cursor: pointer;"
            >아이디 또는 비밀번호를 잊으셨나요?</span>
          </div>
          <b-modal id="findUserModal" hide-footer>
            <template v-slot:modal-title>아이디 찾기</template>
            <div class="d-block text-center">
              <FindUserByEmail />
            </div>
          </b-modal>
        </b-col>
      </b-row>
    </b-container> -->
  </div>
</template>

<script>
import constants from "../../lib/constants.js";
import axios from "axios";

import Join from "../../page/user/Join.vue";
import FindUserByEmail from "../../page/user/FindUserByEmail.vue";

//const SERVER_URL = "http://localhost:8080";

export default {
  name: "NoLoginHome",
  components: { FindUserByEmail, Join },
  props: [],
  computed: {},
  watch: {},
  created() {
    if (constants.IS_LOGED_IN) {
      this.$router.push({ name: constants.URL_TYPE.MAIN.LOGINHOME });
    }
  },
  data: () => {
    return {
      constants,
      emailOrUid: "",
      password: "",
      isLogedin: false,
      // images: {
      //   logo:
      // },
    };
  },
  methods: {
    login() {
      axios
        .post(this.$SERVER_URL + "/account/login/", {
          uidOrEmail: this.emailOrUid,
          password: this.password,
        })
        .then((response) => {
          console.log(response);
          if (response.data.data == "success") {
            localStorage.setItem("uid", response.data.object.uid);
            localStorage.setItem("nickname", response.data.object.nickname);
            localStorage.setItem("email", response.data.object.email);
            localStorage.setItem("password", response.data.object.password);
            constants.IS_LOGED_IN = true;
            this.$router.go({ name: constants.URL_TYPE.MAIN.LOGINHOME });
          } else {
            alert("이메일 또는 비밀번호가 잘못되었습니다.");
          }
        })
        .catch((error) => {
          console.log(error.response);
        });
    },
    close() {
      this.$bvModal.hide("JoinModal");
    },
  },
};
</script>

<style>
#nologinHome {
  background-color: #270949;
  padding-top: 50px;
  color: #e7e7e7;
  text-align: center;
}
#nologinHomeContainer {
  width: 100%;
  margin: 0;
}
.BackColor {
  background-color: #e7e7e7;
}
#Home {
  margin: 150px auto;
}
#AboutUs {
  margin: 0;
  padding: 0;
  width: 100%;
}
#AboutUsCarousel {
  margin: 0 auto;
}
#Ourservice {
  color: black;
  width: 100%;
}
#service {
  background-color: #b6b6b6;
  border-radius: 10px;
  padding: 40px 20px;
  margin: 100px 0;
}
#Contact {
  color: black;
  padding-bottom: 100px;
}
#findUser:hover {
  cursor: pointer;
  text-align: center;
}
.custom-login-style {
  text-align: -webkit-center;
}
</style>
