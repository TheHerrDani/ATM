import { Menu } from "@/types";
import { Component, Vue } from "vue-property-decorator";
import AtmStore from "../store/atmStore";

import LoginPage from "@/components/LoginPage/LoginPage.vue";
import TokenStore from "@/store/TokenStore";
import FooterUI from "../components/FooterUI/FooterUI.vue";
import HeaderUI from "../components/HeaderUI/HeaderUI.vue";
import InfoUI from "../components/InfoUI/InfoUI.vue";
import ProcessesUI from "../components/ProcessesUI/ProcessesUI.vue";
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
