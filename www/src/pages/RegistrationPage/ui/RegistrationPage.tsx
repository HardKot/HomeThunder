import { Grid, Link } from "@mui/material";
import { Registration } from "@/widgets/Registration";
import { LayoutOneComponentComponent } from "@/shared/components/LayoutOneComponentComponent";
import { useRegistrationPage } from "../libs/useRegistrationPage";

export const RegistrationPage = () => {
  const { } = useRegistrationPage();

  return (
    <LayoutOneComponentComponent>
      <Registration />
      <Grid container>
        <Grid item>
          <Link href="/login">Войти</Link>
        </Grid>
      </Grid>
    </LayoutOneComponentComponent>
  );
};
