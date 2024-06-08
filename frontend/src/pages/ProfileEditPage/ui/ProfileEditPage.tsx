"use client";

import { LayoutAppComponent } from "@/shared/components/LayoutAppComponent";
import { AppBar } from "@/widgets/AppBar";
import { ProfileEdit } from "@/widgets/ProfileEdit";

export const ProfileEditPage = () => {
  return (
    <LayoutAppComponent header={<AppBar />}>
      <ProfileEdit />
    </LayoutAppComponent>
  );
};
