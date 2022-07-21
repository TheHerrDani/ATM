import {
  Action,
  getModule,
  Module,
  Mutation,
  VuexModule
} from "vuex-module-decorators";
import store from "./store";

@Module({ dynamic: true, namespaced: true, name: "TokenStore", store })
class TokenStore extends VuexModule {
  token = "";
  loginError = "";

  headerConfig: { headers: { Authorization: string } } = {
    headers: { Authorization: `Bearer ${this.token}` },
  };

  public get getLoginError(): string {
    return this.loginError;
  }

  @Mutation
  public setTokenMutation(newToken: string) {
    this.token = newToken;
    this.headerConfig = {
      headers: { Authorization: `Bearer ${newToken}` },
    };
  }

  @Mutation
  public setLoginErrorMutation(loginError: string) {
    this.loginError = loginError;
  }

  @Action({ commit: "setTokenMutation", rawError: true })
  public async setToken(token: string) {
    return token;
  }

  @Action({ commit: "setLoginErrorMutation", rawError: true })
  public async setLoginError(loginError: string) {
    return loginError;
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
