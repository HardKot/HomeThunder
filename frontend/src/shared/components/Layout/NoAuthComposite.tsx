import { CssBaseline, Grid, Paper } from "@mui/material";

import {PropsWithChildren} from "react";

export const NoAuthComposite = ({ children }: PropsWithChildren) => (
  <Grid container component={"main"} className={"h-screen"}>
    <CssBaseline />

    <Grid
      item
      xs={12}
      md={4}
      component={Paper}
      elevation={6}
      className={"justify-self-center content-center p-5 justify-items-center"}
      margin={"normal"}
    >
      {children}
    </Grid>
    <Grid
      item
      md={8}
      sx={{
        backgroundImage: "url(/static/background.jpeg)",
        backgroundRepeat: "no-repeat",
        backgroundSize: "cover",
        backgroundPosition: "center",
      }}
      xs={false}
    />
  </Grid>
)
