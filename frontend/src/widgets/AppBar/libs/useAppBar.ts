"use client";

import { useScrollTrigger } from "@mui/material";
import { useCallback, useState } from "react";
import { useRouter } from "next/navigation";

export const useAppBar = () => {
  const [openDrawer, setOpenDrawer] = useState(false);
  const router = useRouter();

  const trigger = useScrollTrigger({
    disableHysteresis: true,
    threshold: 0,
    target: window ? window : undefined,
  });

  const onOpenDrawer = useCallback(() => setOpenDrawer(true), []);
  const onCloseDrawer = useCallback(() => setOpenDrawer(false), []);

  const navigateEditProfile = useCallback(
    () => router.push("/profileEdit"),
    [],
  );

  return {
    openDrawer,
    appBarElevation: trigger ? 4 : 0,
    onOpenDrawer,
    onCloseDrawer,
    navigateEditProfile,
  };
};
