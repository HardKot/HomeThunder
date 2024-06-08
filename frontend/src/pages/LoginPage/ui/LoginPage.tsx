import { Box, CssBaseline, Grid, Link, Paper, Typography } from "@mui/material";

export const LoginPage = () => (
  <Grid container component={"main"} sx={{ height: "100vh" }}>
    <CssBaseline />

    <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
      <Box
        sx={{
          my: 8,
          mx: 4,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <Typography component="h1" variant="h5">
          Вход
        </Typography>
      </Box>
    </Grid>
    <Grid
      item
      sm={4}
      md={7}
      sx={{
        backgroundImage: "url(/static/background.jpeg)",
        backgroundRepeat: "no-repeat",
        backgroundSize: "cover",
        backgroundPosition: "center",
      }}
      xs={false}
    />
  </Grid>
);
