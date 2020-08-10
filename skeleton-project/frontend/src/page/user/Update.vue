<template>
  <div id="update">
    <b-card class="update_card" header-tag="header" footer-tag="footer">
      <template v-slot:header>
        <h4 class="mb-0 mt-2 mb-2">회원정보수정</h4>
        <!-- 사용자 닉네임 잘 들어가는지 확인하기 -->
        <p class="mb-2">{{ nickName }}님의 회원 정보를 수정할 수 있습니다.</p>
      </template>
      <b-card-text>
        <b-container>
          <b-row>
            <b-col class="my-auto" align-self="stretch center">
              <!-- 파일형식 제한두기 accept="image/jpeg,video/*[, MIME_TYPES]" -->
              <input
                type="file"
                ref="profileImg"
                style="display: none"
                accept="image/jpeg, jpg, png/"
                @change="uploadImage($event)"
              />

              <button id="profileImgBtn" @click="$refs.profileImg.click()">
                <!-- 사용자의 프로필사진 url 첨부 -->
                <!-- <b-avatar src="" size="10rem" ref="uploadItemImage" ></b-avatar> -->
                <img
                  class="profileImg"
                  ref="uploadItemImage"
                  accept="image/jpeg, jpg, png/"
                  src
                  style="width: 10rem; height: 10rem;"
                />
                <p class="mt-2 mb-0">프로필 변경</p>
              </button>
            </b-col>
            <b-col>
              <b-container>
                <b-row class="my-1">
                  <b-col role="group" cols="9">
                    <b-form-text id="email-help">
                      아이디, 닉네임, 이메일 변경 시 중복확인이
                      필요합니다.
                    </b-form-text>
                  </b-col>
                </b-row>
                <b-row class="my-1">
                  <b-col role="group" cols="9">
                    <b-form-input
                      id="uid"
                      v-model="uid"
                      :state="uidState"
                      aria-describedby="uid-help uid-feedback"
                      placeholder="아이디를 입력해주세요"
                      trim
                      type="text"
                    ></b-form-input>
                    <b-form-invalid-feedback id="uid-feedback">{{uidFeedback}}</b-form-invalid-feedback>
                  </b-col>
                </b-row>
                <b-row class="my-1">
                  <b-col role="group" cols="9">
                    <b-form-input
                      id="nickName"
                      v-model="nickName"
                      :state="nickNameState"
                      aria-describedby="nickName-help nickName-feedback"
                      placeholder="닉네임을 입력해주세요"
                      trim
                      type="text"
                    ></b-form-input>
                    <b-form-invalid-feedback id="nickName-feedback">{{nicknameFeedback}}</b-form-invalid-feedback>
                  </b-col>
                </b-row>
                <b-row class="my-1">
                  <b-col role="group" cols="9">
                    <b-form-input
                      id="email"
                      v-model="email"
                      :state="emailState"
                      aria-describedby="email-help email-feedback"
                      placeholder="이메일을 입력해주세요"
                      trim
                      type="text"
                    ></b-form-input>
                    <b-form-invalid-feedback id="email-feedback">{{emailFeedback}}</b-form-invalid-feedback>
                  </b-col>
                </b-row>
                <b-row class="my-1">
                  <b-col role="group" class="password-wrap" cols="9">
                    <b-form-input
                      id="password"
                      v-model="password"
                      placeholder="비밀번호를 입력해주세요"
                      :state="passwordState"
                      aria-describedby="password-help password-feedback"
                      trim
                      :type="passwordType"
                    ></b-form-input>
                    <span :class="{ active: passwordType === 'text' }">
                      <i class="fas fa-eye"></i>
                    </span>
                    <b-form-invalid-feedback id="password-feedback">{{passwordFeedback}}</b-form-invalid-feedback>
                  </b-col>
                </b-row>
                <b-row class="my-1">
                  <b-col role="group" class="password-wrap" cols="9">
                    <b-form-input
                      id="password-confirm"
                      v-model="passwordConfirm"
                      :state="passwordConfirmState"
                      placeholder="비밀번호를 한번 더 입력해주세요"
                      trim
                      :type="passwordConfirmType"
                    ></b-form-input>
                    <span :class="{ active: passwordConfirmType === 'text' }">
                      <i class="fas fa-eye"></i>
                    </span>
                    <b-form-invalid-feedback id="password-feedback">
                      비밀번호를 정확히
                      입력해주세요.
                    </b-form-invalid-feedback>
                  </b-col>
                </b-row>
              </b-container>
            </b-col>
          </b-row>
        </b-container>
      </b-card-text>
      <div class="ml-auto">
        <b-button @click="update" href="#" class="ml-2 mr-2">수정완료</b-button>
        <b-button @click="userdelete" href="#" class="ml-2 mr-2">회원탈퇴</b-button>
      </div>
      <!-- <em>Footer Slot</em> -->
    </b-card>
  </div>
</template>

<script>
import constants from "../../lib/constants";
import axios from "axios";

//const SERVER_URL = "http://localhost:8080";

export default {
  name: "Update",
  components: {},
  created() {},
  mounted() {
    let itemImage = this.$refs.uploadItemImage; //img dom 접근

    axios
      .get(this.$SERVER_URL + `/account/ckprofile/${localStorage["uid"]}`)
      .then((response) => {
        if (response.data.status) {
          console.log(response);
          if (response.data.object == null) {
            console.log("등록된 프로필 이미지가 없습니다.");
            itemImage.src =
              "https://cdn4.iconfinder.com/data/icons/small-n-flat/24/user-alt-512.png";
          } else {
            console.log("등록된 프로필 이미지가 있습니다.");
            itemImage.src =
              this.$SERVER_URL + `/account/profile/${localStorage["uid"]}`;
          }
        } else {
          console.log("프로필 이미지 가져오는 중 에러 발생");
          console.log(response);
        }
      })
      .catch((error) => {
        console.log(error.response);
      });
  },
  watch: {},
  data: () => {
    return {
      constants,
      uid: localStorage["uid"],
      nickName: localStorage["nickname"],
      email: localStorage["email"],
      password: "",
      passwordConfirm: "",
      passwordType: "password",
      passwordConfirmType: "password",
      uidFeedback: "",
      nicknameFeedback: "",
      emailFeedback: "",
      passwordFeedback: "",
      uidDupcheck: true,
      nickDupcheck: true,
      emailDupcheck: true,
      passwordDupcheck: true,
      newPassword: "",
    };
  },
  computed: {
    uidState() {
      if (this.uid.length > 0 ? true : false) {
        if (localStorage["uid"] != this.uid) {
          let form = new FormData();
          form.append("uid", this.uid);
          axios
            .post(this.$SERVER_URL + "/account/signup/idcheck", form)
            .then((response) => {
              console.log(response.data.status);
              this.uidDupcheck = response.data.status;
              console.log(uidDupcheck);
            })
            .catch((error) => {
              console.log(error.response);
            });
        }
        if (this.uidDupcheck) {
          return true;
        } else {
          this.uidFeedback = "아이디가 중복되었습니다.";
          return false;
        }
      } else {
        this.uidFeedback = "필수 입력값입니다.";
        return false;
      }
    },
    nickNameState() {
      if (this.nickName.length > 0 ? true : false) {
        if (localStorage["nickname"] != this.nickName) {
          let form = new FormData();
          form.append("nickname", this.nickName);
          axios
            .post(this.$SERVER_URL + "/account/signup/nicknamecheck", form)
            .then((response) => {
              this.nickDupcheck = response.data.status;
            })
            .catch((error) => {
              console.log(error.response);
            });
        }
        if (this.nickDupcheck) {
          return true;
        } else {
          this.nicknameFeedback = "닉네임이 중복되었습니다.";
          return false;
        }
      } else {
        this.nicknameFeedback = "필수 입력값입니다.";
        return false;
      }
    },
    emailState() {
      if (
        this.email.indexOf("@") < 0 ||
        this.email.indexOf("@") >= this.email.indexOf(".com")
      ) {
        this.emailFeedback = "이메일 형식이 맞지 않습니다.";
        return false;
      } else {
        console.log(localStorage["email"]);
        console.log(this.email);
        if (localStorage["email"] != this.email) {
          let form = new FormData();
          form.append("email", this.email);
          axios
            .post(this.$SERVER_URL + "/account/signup/emailcheck", form)
            .then((response) => {
              this.emailDupcheck = response.data.status;
            })
            .catch((error) => {
              console.log(error.response);
            });
        }
        if (this.emailDupcheck) {
          return true;
        } else {
          this.emailFeedback = "이메일이 중복되었습니다.";
          return false;
        }
      }
    },
    passwordState() {
      if (this.password.length > 0) {
        var pattern1 = /[0-9]/;
        var pattern2 = /[a-zA-Z]/;
        if (
          !pattern1.test(this.password) ||
          !pattern2.test(this.password) ||
          this.password.length < 8
        ) {
          this.passwordFeedback =
            "비밀번호는 8자리 이상 문자, 숫자로 작성해주세요.";
          return false;
        } else if (localStorage["password"] == this.password) {
          this.passwordFeedback =
            "변경하려는 비밀번호와 이전 비밀번호가 동일합니다.";
          return false;
        } else {
          return true;
        }
      }
    },
    passwordConfirmState() {
      if (this.password) {
        if (this.password === this.passwordConfirm) {
          return true;
        } else {
          return false;
        }
      }
    },
  },
  methods: {
    uploadImage(event) {
      const formData = new FormData();
      formData.append("profile", event.target.files[0]);

      //const file = event.target.files[0];
      axios
        .post(
          this.$SERVER_URL + `/account/profile/${localStorage["uid"]}`,
          formData,
          {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          }
        )
        .then((response) => {
          console.log(response);
          if (response.data.status) {
            alert("프로필 업로드 성공");
            let itemImage = this.$refs.uploadItemImage; //img dom 접근
            itemImage.src =
              this.$SERVER_URL + `/account/profile/${localStorage["uid"]}`;
          } else {
            alert("프로필 업로드 실패");
          }
        });
    },
    update() {
      if (this.uidDupcheck && this.nickDupcheck && this.emailDupcheck) {
        if (this.password.length) {
          this.newPassword = this.password;
        } else {
          this.newPassword = localStorage["password"];
        }
       
        axios
          .put(this.$SERVER_URL + `/account`, {
            uid: this.uid,
            nickname: this.nickName,
            email: this.email,
            password: this.newPassword,
          })
          .then((response) => {
            console.log(response);
            if (response.data.status) {
              alert("회원정보가 수정되었습니다!");
            } else {
              alert("수정된 정보가 반영되지 않았습니다.");
            }
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        alert("변경할 정보를 다시 확인해주세요.");
      }
    },

    userdelete() {
      alert("정말로 탈퇴 하시겠습니까?");
      axios
        .delete(this.$SERVER_URL + `/account/${localStorage["uid"]}`, {
          params: { uid: localStorage["uid"] },
        })
        .then((response) => {
          console.log(response);
          if (response.data.status) {
            localStorage.removeItem("uid");
            localStorage.removeItem("password");
            localStorage.removeItem("email");
            localStorage.removeItem("createDate");
            constants.IS_LOGED_IN = false;
            alert("회원정보가 삭제되었습니다");
            $router.push;
          }
        })
        .catch((error) => {
          console.log(error.response);
        });
    },
  },
};
</script>

<style>
#update {
  width: 900px;
  margin-top: 50px;
  margin-left: auto;
  margin-right: auto;
  text-align: center;
}
.update_card {
  width: 100%;
}
#profileImgBtn {
  border: 0;
  /* border-radius: 50%; */
  background-color: white;
}
.profileImg {
  border-radius: 50%;
}
</style>