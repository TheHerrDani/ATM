import { Component, Vue } from "vue-property-decorator";
import AtmStore from "../store/atmStore";
import { Menu } from "@/types";

import HeaderUI from "../components/HeaderUI/HeaderUI.vue";
import ProcessesUI from "../components/ProcessesUI/ProcessesUI.vue";
import FooterUI from "../components/FooterUI/FooterUI.vue";
import InfoUI from "../components/InfoUI/InfoUI.vue";
import TokenStore from "@/store/TokenStore";
import LoginPage from "@/components/LoginPage/LoginPage.vue";
@Component({
  components: {
    LoginPage,
    HeaderUI,
    ProcessesUI,
    FooterUI,
    InfoUI,
  },
})
export default class App extends Vue {
  menuEnum = Menu;

  get selectedMenu() {
    return AtmStore.getSelectedMenu;
  }

  get isLoggidIn(): boolean {
    return TokenStore.isLoggedIn;
  }
}
