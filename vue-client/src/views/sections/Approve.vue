<template>
  <base-section id="approve">
    <base-heading title="결제완료" />

    <base-subheading
      space="8"
      title="결제가 완료되었습니다."
    />

    <div class="text-center">
      <base-btn :to="{ name: 'Home' }">
        홈으로 돌아가기
      </base-btn>
    </div>
  </base-section>
</template>

<script>
  export default {
    name: 'Approve',

    provide: {
      heading: { align: 'center' },
    },
  }
</script>
