<template>
  <div id="Compare">
    <b-img :src="require(`@/assets/img/animat-lightbulb-color.gif`)" contain width="300px"></b-img>
    <h1 class="Message">가입 인증 단계입니다.</h1>
    <h3 class="Message">인증을 완료하고 간편하게 IT포트폴리오를 준비해보세요!</h3>
    <div id="Btn" @click="compare()">인증 완료</div>
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
    compare() {
      //   요기다가 비교요청아어ㅏ어아ㅣㅇㄴ
    },
  },
};
</script>

<style>
#Compare {
  width: 80%;
  margin: 50px auto;
  text-align: center;
}
.Message {
  margin-bottom: 50px;
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