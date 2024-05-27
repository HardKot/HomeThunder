'use client'

import {
  IconButton,
  Toolbar,
  AppBar as MUIAppBar,
  useScrollTrigger,
  Drawer,
  Accordion,
  AccordionSummary, List, ListItem, ListItemButton, ListItemIcon, ListItemText, Typography
} from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import {useAppBar} from "@/widgets/AppBar/libs/useAppBar";
import {Settings} from "@mui/icons-material";

export const AppBar = () => {
  const { appBarElevation, openDrawer, onOpenDrawer,
    onCloseDrawer, navigateEditProfile } = useAppBar()
  return (<>
    <MUIAppBar position="static" elevation={appBarElevation}>
      <Toolbar>
        <IconButton
          size="large"
          edge="start"
          color="inherit"
          aria-label="menu"
          sx={{mr: 2}}
          onClick={onOpenDrawer}
        >
          <MenuIcon/>
        </IconButton>
      </Toolbar>
    </MUIAppBar>
    <Drawer elevation={3} onClose={onCloseDrawer} open={openDrawer}>
      <List>
        <ListItem disablePadding>
          <Accordion>
            <AccordionSummary
              expandIcon={<Settings />}
              aria-controls="panel2-content"
              id="panel2-header"
            >
              <Typography>Настройки</Typography>
            </AccordionSummary>
            <ListItemButton>
              <ListItemText primary="Редактировать профиль" onClick={navigateEditProfile}/>
            </ListItemButton>
          </Accordion>
        </ListItem>
      </List>
    </Drawer>
  </>)
}

