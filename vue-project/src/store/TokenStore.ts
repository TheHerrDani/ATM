import {
  Action,
  getModule,
  Module,
  Mutation,
  VuexModule,
} from "vuex-module-decorators";
import store from "./store";

@Module({ dynamic: true, namespaced: true, name: "TokenStore", store })
class TokenStore extends VuexModule {
  token = "";
  headerConfig: { headers: { Authorization: string } } = {
    headers: { Authorization: `Bearer ${this.token}` },
  };

  @Mutation
  public setTokenMutation(newToken: string) {
    this.token = newToken;
    this.headerConfig = {
      headers: { Authorization: `Bearer ${newToken}` },
    };
  }

  @Action({ commit: "setTokenMutation", rawError: true })
  public async setToken(token: string) {
    return token;
  }

  public get getHeaderConfig(): { headers: { Authorization: string } } {
    return this.headerConfig;
  }

  public get isLoggedIn(): boolean {
    if (this.token && this.token.length > 0) {
      return true;
    } else {
      return false;
    }
  }
}
export default getModule(TokenStore);
