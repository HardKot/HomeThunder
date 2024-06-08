import {
  AppBar,
  Autocomplete,
  Box,
  Icon,
  TextField,
  Typography,
} from "@mui/material";
import ThunderstormIcon from "@mui/icons-material/Thunderstorm";
import { useStyle } from "@/shared/react-utils/useStyle";
import { ReactNode } from "react";

interface HeaderProps {
  name: string;
  searcher?: ReactNode;
  accountButton?: ReactNode;
  notifications?: ReactNode;
}

export const Header = ({
  name,
  searcher,
  notifications,
  accountButton,
}: HeaderProps) => {
  const style = useStyle((theme) => ({}));

  return (
    <AppBar>
      <Box>
        <ThunderstormIcon color={"primary"} />
        <Typography variant="h1" component="h2">
          {name}
        </Typography>
        {searcher}
      </Box>
      <Box>
        {notifications}
        {accountButton}
      </Box>
    </AppBar>
  );
};
