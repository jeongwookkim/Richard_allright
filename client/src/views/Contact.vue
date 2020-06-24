<template>
  <div>
    <h1>컨택트 컴포넌트</h1>
    <div>
      <p>공유 주차장 신청을 원하시는 분들은 아래로 연락주세요!</p>
      <p>mybiggold@gmail.com</p>
      <p>약관동의 컴포넌트 확인</p>
      <Yaggwan />
    </div>
  </div>
</template>

<script>
import Yaggwan from "../components/Yaggwan.vue"
export default {
  name: "Contact",
  components: {
    Yaggwan
  }
};
</script>

<style></style>
