<template>
  <div id="pageNotFound">
    <b-img :src="require(`@/assets/img/animat-search-color.gif`)" contain width="300px"></b-img>
    <h1 id="Message">페이지를 찾을 수 없습니다.</h1>
    <div id="Btn" @click="gotoMain()">메인페이지로 이동</div>
  </div>
</template>

<script>
import constants from "../../lib/constants";

export default {
  name: "PageNotFound",
  data: () => {
    return {
      constants,
    };
  },
  methods: {
    gotoMain() {
      if (localStorage["uid"]) {
        this.$router.push({ name: constants.URL_TYPE.MAIN.LOGINHOME });
      } else {
        this.$router.push({ name: constants.URL_TYPE.MAIN.NOLOGINHOME });
      }
    },
  },
};
</script>

<style>
#pageNotFound {
  width: 80%;
  margin: 50px auto;
  text-align: center;
}
#Message {
  margin-bottom: 70px;
}
#Btn {
  display: inline;
  border-radius: 5px;
  background-color: #7a63ff;
  color: white;
  padding: 10px;
}
#btn:hover {
  cursor: pointer;
}
</style>