'use client'

import { PropsWithChildren } from "react";
import { Paper, Grid, CssBaseline } from "@mui/material";

export interface LayoutOneComponentProps extends PropsWithChildren {}

export const LayoutOneComponentComponent = ({
  children,
}: LayoutOneComponentProps) => (
  <Grid container component="main" sx={{ height: "100vh" }}>
    <CssBaseline />
    <Grid
      item
      xs={false}
      sm={4}
      md={7}
      sx={{
        backgroundImage: "url(/background.jpeg)",
        backgroundRepeat: "no-repeat",
        backgroundColor: (t) =>
          t.palette.mode === "light" ? t.palette.grey[50] : t.palette.grey[900],
        backgroundSize: "cover",
        backgroundPosition: "center",
      }}
    />
    <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
      {children}
    </Grid>
  </Grid>
);
