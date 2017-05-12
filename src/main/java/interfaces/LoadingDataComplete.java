package interfaces;

import retrofit.model.*;

import java.util.List;

public interface LoadingDataComplete {
    void onOrganizationsWreckersComplete(List<OrganizationItem> organizationItems);

    void onManufacturesModelsComplete(List<ManufactureItem> manufactureItems);

    void onClausesComplete(List<ClauseItem> clauseItems);

    void onPoliceDepartmentsPolicemanComplete(List<PoliceDepartmentItem> policeDepartmentItems);

    void onColorComplete(List<ColorItem> colorItems);

    void onDataComplete(List<OrganizationItem> organizationItems, List<ManufactureItem> manufactureItems, List<ClauseItem> clauseItems, List<PoliceDepartmentItem> policeDepartmentItems);
}
