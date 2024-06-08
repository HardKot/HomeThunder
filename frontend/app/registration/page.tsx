import { LayoutOneComponentComponent } from "@/shared/components/LayoutOneComponentComponent";
import { Registration } from "@/widgets/Registration";
import { Grid, Link } from "@mui/material";

export default function Page() {
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
}
