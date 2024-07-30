<template>
  <div ref="container"></div>
</template>
<script>
import * as THREE from "three";
import {OBJLoader} from "three/examples/jsm/loaders/OBJLoader.js";
import { OrbitControls } from "three/examples/jsm/controls/OrbitControls.js";//

export default {
  props:{
    modelUrl:String,
  },
  data(){
    return{
    }
  },
  mounted() {
    const container = this.$refs.container;
    // 创建场景、相机、渲染器等
    const scene = new THREE.Scene();
    const camera = new THREE.PerspectiveCamera(75, container.clientWidth / container.clientHeight, 0.1, 1000);
    const renderer = new THREE.WebGLRenderer({alpha:true});
    // scene.background =new THREE.Color(0.1, 0.1, 0.1, 0.1);
    // scene.fog = new THREE.Fog(0xffffff, 2, 15)
    // 添加两个光源，一个黄、一个白
    scene.add(new THREE.HemisphereLight(0x443333, 0x111122))
    addShadowedLight(1, 1, 15, 0xffffff, 1)
    addShadowedLight(0.5, 1, -15, 0xffffff, 1)
    // const light = new THREE.PointLight(0xffffff, 1, 100);
    // const light1 = new THREE.PointLight(0xffffff, 1, 100);
    // const light2 = new THREE.PointLight(0xffffff, 1, 100);
    // const light3 = new THREE.PointLight(0xffffff, 1, 100);
    // light1.position.set(0,0,10);
    // light.position.set(0, 10, -10);
    // light2.position.set(10,10,0)
    // light3.position.set(-10,-10,0)
    // scene.add(light);
    // scene.add(light1)
    // scene.add(light2)
    // scene.add(light3)
    camera.position.set(15, 15, 15); // 将相机向后移动五个单位
    camera.lookAt(new THREE.Vector3(0, 0, 0)); // 将相机方向对准原点
    renderer.setSize(container.clientWidth, container.clientHeight);
    container.appendChild(renderer.domElement);
// 加载模型
    const loader = new OBJLoader();
    loader.load(this.modelUrl, function (object) {
          // 添加模型到场景
          scene.add(object);
        },

    );
    //
    function addShadowedLight(x, y, z, color, intensity) {
      const directionalLight = new THREE.DirectionalLight(color, intensity)
      directionalLight.position.set(x, y, z)
      scene.add(directionalLight)
    }
    // 创建控制器，用于处理鼠标事件
    const controls = new OrbitControls(camera, renderer.domElement);

// 渲染循环
    function animate() {
      requestAnimationFrame(animate);
      renderer.render(scene, camera);
      if (loader) {
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
          loader.position.x += moveX;
          loader.position.y += moveY;

          // 渲染场景
          renderer.render(scene, camera);
        }
      }

    }

    animate();
  },
};
</script>
