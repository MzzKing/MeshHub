<template>
  <div ref="container"></div>
</template>

<script>
import * as THREE from "three";
import { OrbitControls } from "three/examples/jsm/controls/OrbitControls.js";
import {OBJLoader} from "three/examples/jsm/loaders/OBJLoader.js";

export default {
  mounted() {
    const container = this.$refs.container;

    // 创建场景、相机、渲染器等
    const scene = new THREE.Scene();
    const camera = new THREE.PerspectiveCamera(
        75,
        container.clientWidth / container.clientHeight,
        0.1,
        1000
    );
    const renderer = new THREE.WebGLRenderer();
    renderer.setSize(container.clientWidth, container.clientHeight);
    container.appendChild(renderer.domElement);

    // 加载模型
    let object = null;
    const loader = new OBJLoader();
    loader.load(
        "models/Ram.obj",
        function (loadedObject) {
          object = loadedObject;
          scene.add(object);
        },
        function (xhr) {
          console.log((xhr.loaded / xhr.total) * 100 + "% loaded");
        },
        function (error) {
          console.log("An error happened" + error);
        }
    );

    // 添加光源
    const light = new THREE.DirectionalLight(0xffffff, 1);
    light.position.set(0, 1, 1);
    scene.add(light);

    // 创建控制器，用于处理鼠标事件
    const controls = new OrbitControls(camera, renderer.domElement);

    // 渲染循环
    function animate() {
      requestAnimationFrame(animate);
      if (object) {
        // 使控制器生效
        controls.update();

        // 监听鼠标事件
        const mouseState = controls.mouseButtons.LEFT;
        if (mouseState === THREE.MOUSE.MIDDLE) {
          // 计算鼠标在水平和垂直方向上的位移量
          const deltaX = controls.getAzimuthalAngle() - controls.getPolarAngle();
          const deltaY = controls.getDistance();

          // 根据位移量计算模型应该沿着哪个方向移动
          const moveX = deltaX / Math.PI * 10;
          const moveY = deltaY / 10;

          // 将计算出的位移量应用到模型上
          object.position.x += moveX;
          object.position.y += moveY;

          // 渲染场景
          renderer.render(scene, camera);
        }
      }
    }
    animate();
  },
};
</script>