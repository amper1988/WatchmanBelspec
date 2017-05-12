package model;


import interfaces.LoadingDataComplete;
import javafx.application.Platform;
import retrofit.Api;
import retrofit.RetrofitService;
import retrofit.model.*;
import retrofit.model.get_clauses.request.GetClausesRequestEnvelope;
import retrofit.model.get_clauses.response.GetClausesResponseEnvelope;
import retrofit.model.get_colors.request.GetColorsRequestEnvelope;
import retrofit.model.get_colors.response.GetColorsResponseEnvelope;
import retrofit.model.get_manufactures_with_models.request.GetManufacturesWithModelsRequestEnvelope;
import retrofit.model.get_manufactures_with_models.response.GetManufacturesWithModelsResponseEnvelope;
import retrofit.model.get_organizations_with_employers.request.GetOrganizationsWithEmployersRequestEnvelope;
import retrofit.model.get_organizations_with_employers.response.GetOrganizationsWithEmployersResponseEnvelope;
import retrofit.model.get_police_department_with_employers.request.GetPoliceDepartmentWithEmployersRequestEnvelope;
import retrofit.model.get_police_department_with_employers.response.GetPoliceDepartmentWithEmployersResponseEnvelope;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.Converter;
import utils.Encode;
import utils.UserManager;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class LoadingDataFormGetToParkingComplete {
    private boolean manufactureModelComplete;
    private boolean colorComplete;
    private boolean clauseComplete;
    private boolean organizationWreckerComplete;
    private boolean policeDepartmentPolicemanComplete;
    private List<ManufactureItem> manufactureItems;
    private List<ColorItem> colorItems;
    private List<PoliceDepartmentItem> policeDepartmentItems;
    private List<ClauseItem> clauseItems;
    private List<OrganizationItem> organizationItems;

    private LoadingDataComplete listener;
    private RetrofitService retrofitService;

    private int getClausesCount = 0;
    private int getColorsCount = 0;
    private int getManufacturesCount = 0;
    private int getPoliceDepartmentCount = 0;
    private int getOrganizationCount = 0;

    public LoadingDataFormGetToParkingComplete(LoadingDataComplete listener) {
        this.manufactureModelComplete = false;
        this.colorComplete = false;
        this.clauseComplete = false;
        this.organizationWreckerComplete = false;
        this.policeDepartmentPolicemanComplete = false;
        retrofitService = Api.createRetrofitService();
        this.listener = listener;
    }

    public void loadData() {
        this.manufactureModelComplete = false;
        this.colorComplete = false;
        this.clauseComplete = false;
        this.organizationWreckerComplete = false;
        this.policeDepartmentPolicemanComplete = false;
        //getClauses
        getClauses();
        //getColors
        getColors();
        //getManufactureModel
        getManufactureModel();
        //getPoliceDepartment
        getPoliceDepartment();
        //getOrganization
        getOrganization();

    }

    private void getOrganization() {
        retrofitService.executeGetOrganizationsWithEmployers(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                new GetOrganizationsWithEmployersRequestEnvelope()).enqueue(new Callback<GetOrganizationsWithEmployersResponseEnvelope>() {
            @Override
            public void onResponse(Call<GetOrganizationsWithEmployersResponseEnvelope> call, final Response<GetOrganizationsWithEmployersResponseEnvelope> response) {
                getOrganizationCount = 0;
                organizationWreckerComplete = true;
                if (response.code() == 200) {
                    organizationItems = response.body().getOrganizationItemList();
                } else {
                    organizationItems = null;
                    Platform.runLater(() -> Utils.showAlertMessage("Response error.", Converter.convertResponseToSting(response.errorBody())));
                }
                if (listener != null) {
                    listener.onOrganizationsWreckersComplete(response.body().getOrganizationItemList());
                }
                if (loadingIsComplete() && listener != null) {
                    listener.onDataComplete(organizationItems, manufactureItems, clauseItems, policeDepartmentItems);
                }
            }

            @Override
            public void onFailure(Call<GetOrganizationsWithEmployersResponseEnvelope> call, final Throwable t) {
                if (getOrganizationCount++ < 6) {
                    getOrganization();
                } else {
                    getOrganizationCount = 0;
                    organizationWreckerComplete = true;
                    organizationItems = null;
                    if (listener != null) {
                        listener.onOrganizationsWreckersComplete(null);
                    }
                    if (loadingIsComplete() && listener != null) {
                        listener.onDataComplete(organizationItems, manufactureItems, clauseItems, policeDepartmentItems);
                    }
                    Platform.runLater(() -> Utils.showAlertMessage("Response error.", t.getMessage()));
                }
            }
        });
    }

    private void getPoliceDepartment() {
        retrofitService.executeGetPoliceDepartmentWithEmployers(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                new GetPoliceDepartmentWithEmployersRequestEnvelope()).enqueue(new Callback<GetPoliceDepartmentWithEmployersResponseEnvelope>() {
            @Override
            public void onResponse(Call<GetPoliceDepartmentWithEmployersResponseEnvelope> call, final Response<GetPoliceDepartmentWithEmployersResponseEnvelope> response) {
                policeDepartmentPolicemanComplete = true;
                getPoliceDepartmentCount = 0;
                if (response.code() == 200) {
                    policeDepartmentItems = response.body().getPoliceDepartmentList();


                } else {
                    policeDepartmentItems = null;
                    Platform.runLater(() -> Utils.showAlertMessage("Response error.", Converter.convertResponseToSting(response.errorBody())));
                }
                if (listener != null) {
                    listener.onPoliceDepartmentsPolicemanComplete(response.body().getPoliceDepartmentList());
                }
                if (loadingIsComplete() && listener != null) {
                    listener.onDataComplete(organizationItems, manufactureItems, clauseItems, policeDepartmentItems);
                }
            }

            @Override
            public void onFailure(Call<GetPoliceDepartmentWithEmployersResponseEnvelope> call, final Throwable t) {
                if (getPoliceDepartmentCount++ < 6) {
                    getPoliceDepartment();
                } else {
                    getPoliceDepartmentCount = 0;
                    policeDepartmentPolicemanComplete = true;
                    policeDepartmentItems = null;
                    if (listener != null) {
                        listener.onPoliceDepartmentsPolicemanComplete(null);
                    }
                    if (loadingIsComplete() && listener != null) {
                        listener.onDataComplete(organizationItems, manufactureItems, clauseItems, policeDepartmentItems);
                    }
                    Platform.runLater(() -> Utils.showAlertMessage("Request fail.", t.getMessage()));
                }
            }
        });
    }

    private void getManufactureModel() {
        retrofitService.executeGetManufacturesWithModels(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                new GetManufacturesWithModelsRequestEnvelope()).enqueue(new Callback<GetManufacturesWithModelsResponseEnvelope>() {
            @Override
            public void onResponse(Call<GetManufacturesWithModelsResponseEnvelope> call, final Response<GetManufacturesWithModelsResponseEnvelope> response) {
                manufactureModelComplete = true;
                getManufacturesCount = 0;
                if (response.code() == 200) {
                    manufactureItems = response.body().getManufacturesList();

                } else {
                    manufactureItems = null;
                    Platform.runLater(() -> Utils.showAlertMessage("Response error.", Converter.convertResponseToSting(response.errorBody())));
                }
                if (listener != null) {
                    listener.onManufacturesModelsComplete(manufactureItems);
                }
                if (loadingIsComplete() && listener != null) {
                    listener.onDataComplete(organizationItems, manufactureItems, clauseItems, policeDepartmentItems);
                }
            }

            @Override
            public void onFailure(Call<GetManufacturesWithModelsResponseEnvelope> call, final Throwable t) {
                if (getManufacturesCount++ < 6) {
                    getManufactureModel();
                } else {
                    getManufacturesCount = 0;
                    manufactureModelComplete = true;
                    manufactureItems = null;
                    if (listener != null) {
                        listener.onManufacturesModelsComplete(null);
                    }
                    if (loadingIsComplete() && listener != null) {
                        listener.onDataComplete(organizationItems, manufactureItems, clauseItems, policeDepartmentItems);
                    }
                    Platform.runLater(() -> Utils.showAlertMessage("Response error.", t.getMessage()));
                }
            }
        });
    }

    private void getColors() {
        retrofitService.executeGetColors(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                new GetColorsRequestEnvelope()).enqueue(new Callback<GetColorsResponseEnvelope>() {
            @Override
            public void onResponse(Call<GetColorsResponseEnvelope> call, final Response<GetColorsResponseEnvelope> response) {
                colorComplete = true;
                getColorsCount = 0;
                if (response.code() == 200) {
                    colorItems = response.body().getColorList();

                } else {
                    colorItems = null;
                    Platform.runLater(() -> Utils.showAlertMessage("Response error.", Converter.convertResponseToSting(response.errorBody())));
                }
                if (listener != null) {
                    listener.onColorComplete(colorItems);
                }
                if (loadingIsComplete() && listener != null) {
                    listener.onDataComplete(organizationItems, manufactureItems, clauseItems, policeDepartmentItems);
                }
            }

            @Override
            public void onFailure(Call<GetColorsResponseEnvelope> call, final Throwable t) {
                if (getColorsCount++ < 6) {
                    getColors();
                } else {
                    getColorsCount = 0;
                    colorComplete = true;
                    Platform.runLater(() -> Utils.showAlertMessage("Response error.", t.getMessage()));
                    if (listener != null) {
                        listener.onColorComplete(colorItems);
                    }
                    if (loadingIsComplete() && listener != null) {
                        listener.onDataComplete(organizationItems, manufactureItems, clauseItems, policeDepartmentItems);
                    }
                }

            }
        });
    }

    private void getClauses() {
        retrofitService.executeGetClauses(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                new GetClausesRequestEnvelope()).enqueue(new Callback<GetClausesResponseEnvelope>() {
            @Override
            public void onResponse(Call<GetClausesResponseEnvelope> call, final Response<GetClausesResponseEnvelope> response) {
                clauseComplete = true;
                getClausesCount = 0;
                if (response.code() == 200) {
                    clauseItems = response.body().getClauseList();

                } else {
                    clauseItems = null;
                    Platform.runLater(() -> Utils.showAlertMessage("Fail request.", Converter.convertResponseToSting(response.errorBody())));
                }

                if (listener != null) {
                    listener.onClausesComplete(clauseItems);
                }
                if (loadingIsComplete() && listener != null) {
                    listener.onDataComplete(organizationItems, manufactureItems, clauseItems, policeDepartmentItems);
                }
            }

            @Override
            public void onFailure(Call<GetClausesResponseEnvelope> call, final Throwable t) {
                if (getClausesCount++ < 6) {
                    getClauses();
                } else {
                    getClausesCount = 0;
                    clauseComplete = true;
                    clauseItems = null;
                    if (listener != null) {
                        listener.onClausesComplete(null);
                    }
                    if (loadingIsComplete() && listener != null) {
                        listener.onDataComplete(organizationItems, manufactureItems, clauseItems, policeDepartmentItems);
                    }
                    Platform.runLater(() -> Utils.showAlertMessage("Fail request.", t.getMessage()));
                }
            }
        });
    }

    public boolean loadingIsComplete() {
        return (manufactureModelComplete && colorComplete && clauseComplete && organizationWreckerComplete && policeDepartmentPolicemanComplete);
    }

    public List<ManufactureItem> getManufactureItems() {
        return manufactureItems;
    }


    public List<String> getManufactureAsString() {
        ArrayList<String> arrayList = new ArrayList<>();
        if (manufactureItems != null) {
            for (ManufactureItem item : manufactureItems) {
                arrayList.add(item.getName());
            }
        } else {
            arrayList.add("");
        }
        return arrayList;
    }

    public List<String> getModelsAsString(String manufacture) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (ManufactureItem item : manufactureItems) {
            if (item.getName().equals(manufacture)) {
                return item.getModelListAsString();
            }
        }
        arrayList.add("");
        return arrayList;
    }

    public List<ColorItem> getColorItems() {
        return colorItems;
    }

    public List<String> getColorItemsAsString() {
        ArrayList<String> arrayList = new ArrayList<>();
        if (colorItems != null) {
            for (ColorItem item : colorItems) {
                arrayList.add(item.getName());
            }
        } else {
            arrayList.add("");
        }
        return arrayList;
    }

    public List<PoliceDepartmentItem> getPoliceDepartmentItems() {
        return policeDepartmentItems;
    }

    public List<String> getPoliceDepartmentItemsAsString() {
        ArrayList<String> arrayList = new ArrayList<>();
        if (policeDepartmentItems != null) {
            for (PoliceDepartmentItem item : policeDepartmentItems) {
                arrayList.add(item.getName());
            }
        } else {
            arrayList.add("");
        }
        return arrayList;
    }

    public List<String> getPolicemanItemsAsString(String policeDepartment) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (policeDepartmentItems != null) {
            for (PoliceDepartmentItem item : policeDepartmentItems) {
                if (item.getName().equals(policeDepartment)) {
                    return item.getPolicemanListAsString();
                }
            }
        } else
            arrayList.add("");
        return arrayList;
    }

    public List<ClauseItem> getClauseItems() {
        return clauseItems;
    }

    public List<String> getClauseItemsAsString() {
        ArrayList<String> arrayList = new ArrayList<>();
        if (clauseItems != null) {
            for (ClauseItem item : clauseItems) {
                arrayList.add(item.getName());
            }
        } else
            arrayList.add("");
        return arrayList;
    }

    public List<OrganizationItem> getOrganizationItems() {
        return organizationItems;
    }

    public List<String> getOrganizationItemsAsStirng() {
        ArrayList<String> arrayList = new ArrayList<>();
        if (organizationItems != null) {
            for (OrganizationItem item : organizationItems) {
                arrayList.add(item.getName());
            }
        } else {
            arrayList.add("");
        }
        return arrayList;
    }

    public List<String> getWreckerItemsAsString(String organization) {

        if (organizationItems != null) {
            for (OrganizationItem item : organizationItems) {
                if (item.getName().equals(organization)) {
                    return item.getWreckerListAsString();
                }
            }
        }
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("");
        return arrayList;
    }
}
