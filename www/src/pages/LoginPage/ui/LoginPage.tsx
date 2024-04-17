import { LayoutOneComponentComponent } from "@/shared/components/LayoutOneComponentComponent";
import { Grid, Link } from "@mui/material";
import { Login } from "@/widgets/Login/ui/Login";
import { useLoginPage } from "../libs/useLoginPage";

export const LoginPage = () => {
  const {  } = useLoginPage();

  return (
    <LayoutOneComponentComponent>
      <Login />
      <Grid container>
        <Grid item xs>
          <Link href="#" variant="body2">
            Восстановить пароль
          </Link>
        </Grid>
        <Grid item>
          <Link href="/registration" variant="body2">
            Зарегистрироваться
          </Link>
        </Grid>
      </Grid>
    </LayoutOneComponentComponent>
  );
};
