package com.homethunder.homethunder.useCase.staff;

import com.homethunder.homethunder.domain.staff.Shift;

import java.util.List;
import java.util.Optional;

public interface StaffGateway {
    Optional<List<Shift>> getShiftsByEmployee();
}
