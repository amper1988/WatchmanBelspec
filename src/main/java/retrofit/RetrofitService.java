package retrofit;

import retrofit.model.change_parking.request.ChangeParkingRequestEnvelope;
import retrofit.model.change_parking.response.ChangeParkingResponseEnvelope;
import retrofit.model.check_update_watchman.request.CheckUpdateWatchmanRequestEnvelope;
import retrofit.model.check_update_watchman.response.CheckUpdateWatchmanResponseEnvelope;
import retrofit.model.create_arrest.request.CreateArrestRequestEnvelope;
import retrofit.model.create_arrest.response.CreateArrestResponseEnvelope;
import retrofit.model.create_arrest_with_organization_change.request.CreateArrestWithOrganizationChangeRequestEnvelope;
import retrofit.model.create_arrest_with_organization_change.response.CreateArrestWithOrganizationChangeResponseEnvelope;
import retrofit.model.create_back_to_parking.request.CreateBackToParkingRequestEnvelope;
import retrofit.model.create_back_to_parking.response.CreateBackToParkingResponseEnvelope;
import retrofit.model.create_policeman.request.CreatePolicemanRequestEnvelope;
import retrofit.model.create_policeman.response.CreatePolicemanResponseEnvelope;
import retrofit.model.create_receipt.request.CreateReceiptRequestEnvelope;
import retrofit.model.create_receipt.response.CreateReceiptResponseEnvelope;
import retrofit.model.create_recheck_receipt.request.CreateRecheckReceiptRequestEnvelope;
import retrofit.model.create_recheck_receipt.response.CreateRecheckReceiptResponseEnvelope;
import retrofit.model.create_release.request.CreateReleaseRequestEnvelope;
import retrofit.model.create_release.response.CreateReleaseResponseEnvelope;
import retrofit.model.create_release_by_debt_act.request.CreateReleaseByDebtActRequestEnvelope;
import retrofit.model.create_release_by_debt_act.response.CreateReleaseByDebtActResponseEnvelope;
import retrofit.model.create_release_to_another_parking.request.CreateReleaseToAnotherParkingRequestEnvelope;
import retrofit.model.create_release_to_another_parking.response.CreateReleaseToAnotherParkingResponseEnvelope;
import retrofit.model.create_release_without_recheck.request.CreateReleaseWithoutRecheckRequestEnvelope;
import retrofit.model.create_release_without_recheck.response.CreateReleaseWithoutRecheckResponseEnvelope;
import retrofit.model.create_take_from_another_parking.request.CreateTakeFromAnotherParkingRequestEnvelope;
import retrofit.model.create_take_from_another_parking.response.CreateTakeFromAnotherParkingResponseEnvelope;
import retrofit.model.create_uncheck_arrest.request.CreateUncheckArrestRequestEnvelope;
import retrofit.model.create_uncheck_arrest.response.CreateUncheckArrestResponseEnvelope;
import retrofit.model.get_car_details.request.GetCarDetailsRequestEnvelope;
import retrofit.model.get_car_details.response.GetCarDetailsResponseEnvelope;
import retrofit.model.get_car_list_from_base.request.GetCarListFromBaseRequestEnvelope;
import retrofit.model.get_car_list_from_base.response.GetCarListFromBaseResponseEnvelope;
import retrofit.model.get_car_to_parking.request.GetCarToParkingRequestEnvelope;
import retrofit.model.get_car_to_parking.response.GetCarToParkingResponseEnvelope;
import retrofit.model.get_cars_on_evacuation.request.GetCarsOnEvacuationRequestEnvelope;
import retrofit.model.get_cars_on_evacuation.response.GetCarsOnEvacuationResponseEnvelope;
import retrofit.model.get_cars_on_parking.request.GetCarsOnParkingRequestEnvelope;
import retrofit.model.get_cars_on_parking.response.GetCarsOnParkingResponseEnvelope;
import retrofit.model.get_cars_on_paying.request.GetCarsOnPayingRequestEnvelope;
import retrofit.model.get_cars_on_paying.response.GetCarsOnPayingResponseEnvelope;
import retrofit.model.get_clauses.request.GetClausesRequestEnvelope;
import retrofit.model.get_clauses.response.GetClausesResponseEnvelope;
import retrofit.model.get_colors.request.GetColorsRequestEnvelope;
import retrofit.model.get_colors.response.GetColorsResponseEnvelope;
import retrofit.model.get_debt_act_cars.request.GetDebtActCarsRequestEnvelope;
import retrofit.model.get_debt_act_cars.response.GetDebtActCarsResponseEnvelope;
import retrofit.model.get_debt_act_document.request.GetDebtActDocumentRequestEnvelope;
import retrofit.model.get_debt_act_document.response.GetDebtActDocumentResponseEnvelope;
import retrofit.model.get_image_full.request.GetImageFullRequestEnvelope;
import retrofit.model.get_image_full.response.GetImageFullResponseEnvelope;
import retrofit.model.get_manufactures_with_models.request.GetManufacturesWithModelsRequestEnvelope;
import retrofit.model.get_manufactures_with_models.response.GetManufacturesWithModelsResponseEnvelope;
import retrofit.model.get_organization_for_arrest.request.GetOrganizationForArrestRequestEnvelope;
import retrofit.model.get_organization_for_arrest.response.GetOrganizationForArrestResponseEnvelope;
import retrofit.model.get_organizations_with_employers.request.GetOrganizationsWithEmployersRequestEnvelope;
import retrofit.model.get_organizations_with_employers.response.GetOrganizationsWithEmployersResponseEnvelope;
import retrofit.model.get_owner_driver_data.request.GetOwnerDriverDataRequestEnvelope;
import retrofit.model.get_owner_driver_data.response.GetOwnerDriverDataResponseEnvelope;
import retrofit.model.get_parking.request.GetParkingRequestEnvelope;
import retrofit.model.get_parking.response.GetParkingResponseEnvelope;
import retrofit.model.get_parkings.request.GetParkingsRequestEnvelope;
import retrofit.model.get_parkings.response.GetParkingsResponseEnvelope;
import retrofit.model.get_photos.request.GetPhotosRequestEnvelope;
import retrofit.model.get_photos.response.GetPhotosResponseEnvelope;
import retrofit.model.get_police_department.request.GetPoliceDepartmentRequestEnvelope;
import retrofit.model.get_police_department.response.GetPoliceDepartmentResponseEnvelope;
import retrofit.model.get_police_department_with_employers.request.GetPoliceDepartmentWithEmployersRequestEnvelope;
import retrofit.model.get_police_department_with_employers.response.GetPoliceDepartmentWithEmployersResponseEnvelope;
import retrofit.model.get_positions.request.GetPositionsRequestEnvelope;
import retrofit.model.get_positions.response.GetPositionsResponseEnvelope;
import retrofit.model.get_protocol.request.GetProtocolRequestEnvelope;
import retrofit.model.get_protocol.response.GetProtocolResponseEnvelope;
import retrofit.model.get_ranks.request.GetRanksRequestEnvelope;
import retrofit.model.get_ranks.response.GetRanksResponseEnvelope;
import retrofit.model.get_receipt.request.GetReceiptRequestEnvelope;
import retrofit.model.get_receipt.response.GetReceiptResponseEnvelope;
import retrofit.model.get_receipt_information.request.GetReceiptInformationRequestEnvelope;
import retrofit.model.get_receipt_information.response.GetReceiptInformationResponseEnvelope;
import retrofit.model.get_release_information.request.GetReleaseInformationRequestEnvelope;
import retrofit.model.get_release_information.response.GetReleaseInformationResponseEnvelope;
import retrofit.model.get_released_cars.request.GetReleasedCarsRequestEnvelope;
import retrofit.model.get_released_cars.response.GetReleasedCarsResponseEnvelope;
import retrofit.model.get_voucher.request.GetVoucherRequestEnvelope;
import retrofit.model.get_voucher.response.GetVoucherResponseEnvelope;
import retrofit.model.test_connection.request.TestRequestEnvelope;
import retrofit.model.test_connection.response.TestResponseEnvelope;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitService {
    @Headers({
            "Content-Type: application/soap+xml",
            "Accept-Charset: utf-8"
    })
    @POST("ws/testconnection")
    Call<TestResponseEnvelope> executeTestOperation(@Header("Authorization") String auth, @Body TestRequestEnvelope request);

    @Headers({
            "Content-Type: application/soap+xml",
            "Accept-Charset: utf-8"
    })
    @POST("ws/ChangeData")
    Call<ChangeParkingResponseEnvelope> executeChangeParkingOperation(@Header("Authorization") String auth, @Body ChangeParkingRequestEnvelope request);

    @POST("ws/getdata")
    Call<GetParkingResponseEnvelope> executeGetParkingList(@Header("Authorization") String auth, @Body GetParkingRequestEnvelope request);

    @POST("ws/getdata")
    Call<CheckUpdateWatchmanResponseEnvelope> executeCheckUpdateWatchman(@Header("Authorization") String auth, @Body CheckUpdateWatchmanRequestEnvelope request);

    @POST("ws/getdata")
    Call<GetCarsOnParkingResponseEnvelope> executeGetCarOnParking(@Header("Authorization") String auth, @Body GetCarsOnParkingRequestEnvelope request);

    @POST("ws/getdata")
    Call<GetReleasedCarsResponseEnvelope> executeGetReleasedCars(@Header("Authorization") String auth, @Body GetReleasedCarsRequestEnvelope request);

    @POST("ws/getdata")
    Call<GetCarsOnEvacuationResponseEnvelope> executeGetCarsOnEvacuation(@Header("Authorization") String auth, @Body GetCarsOnEvacuationRequestEnvelope request);

    @POST("ws/getdata")
    Call<GetDebtActCarsResponseEnvelope> executeGetDebtActCars(@Header("Authorization") String auth, @Body GetDebtActCarsRequestEnvelope request);

    @POST("ws/getdata")
    Call<GetCarsOnPayingResponseEnvelope> executeGetCarsOnPaying(@Header("Authorization") String auth, @Body GetCarsOnPayingRequestEnvelope request);

    @POST("ws/getdata")
    Call<GetCarDetailsResponseEnvelope> executeGetCarDetails(@Header("Authorization") String auth, @Body GetCarDetailsRequestEnvelope request);

    @POST("ws/getdata")
    Call<GetClausesResponseEnvelope> executeGetClauses(@Header("Authorization") String auth, @Body GetClausesRequestEnvelope request);

    @POST("ws/getdata")
    Call<GetColorsResponseEnvelope> executeGetColors(@Header("Authorization") String auth, @Body GetColorsRequestEnvelope request);

    @POST("ws/getdata")
    Call<GetManufacturesWithModelsResponseEnvelope> executeGetManufacturesWithModels(@Header("Authorization") String auth, @Body GetManufacturesWithModelsRequestEnvelope request);

    @POST("ws/getdata")
    Call<GetPoliceDepartmentWithEmployersResponseEnvelope> executeGetPoliceDepartmentWithEmployers(@Header("Authorization") String auth, @Body GetPoliceDepartmentWithEmployersRequestEnvelope request);

    @POST("ws/getdata")
    Call<GetOrganizationsWithEmployersResponseEnvelope> executeGetOrganizationsWithEmployers(@Header("Authorization") String auth, @Body GetOrganizationsWithEmployersRequestEnvelope request);

    @Headers({
            "Content-Type: application/soap+xml",
            "Accept-Charset: utf-8"
    })
    @POST("ws/CreateData")
    Call<GetCarToParkingResponseEnvelope> executeGetCarToParking(@Header("Authorization") String auth, @Body GetCarToParkingRequestEnvelope request);

    @POST("ws/getdata")
    Call<GetImageFullResponseEnvelope> executeGetImageFull(@Header("Authorization") String auth, @Body GetImageFullRequestEnvelope request);

    @POST("ws/getdata")
    Call<GetReceiptInformationResponseEnvelope> executeGetReceiptInformation(@Header("Authorization") String auth, @Body GetReceiptInformationRequestEnvelope request);

    @Headers({
            "Content-Type: application/soap+xml",
            "Accept-Charset: utf-8"
    })
    @POST("ws/CreateData")
    Call<CreateReceiptResponseEnvelope> executeCreateReceipt(@Header("Authorization") String auth, @Body CreateReceiptRequestEnvelope request);

    @Headers({
            "Content-Type: application/soap+xml",
            "Accept-Charset: utf-8"
    })
    @POST("ws/CreateData")
    Call<CreateReleaseResponseEnvelope> executeCreateRelease(@Header("Authorization") String auth, @Body CreateReleaseRequestEnvelope request);

    @Headers({
            "Content-Type: application/soap+xml",
            "Accept-Charset: utf-8"
    })
    @POST("ws/CreateData")
    Call<CreateReleaseWithoutRecheckResponseEnvelope> executeCreateReleaseWithoutRecheck(@Header("Authorization") String auth, @Body CreateReleaseWithoutRecheckRequestEnvelope request);

    @Headers({
            "Content-Type: application/soap+xml",
            "Accept-Charset: utf-8"
    })
    @POST("ws/CreateData")
    Call<CreateRecheckReceiptResponseEnvelope> executeCreateRecheckReceipt(@Header("Authorization") String auth, @Body CreateRecheckReceiptRequestEnvelope request);

    @Headers({
            "Content-Type: application/soap+xml",
            "Accept-Charset: utf-8"
    })
    @POST("ws/CreateData")
    Call<CreateBackToParkingResponseEnvelope> executeBackToParking(@Header("Authorization") String auth, @Body CreateBackToParkingRequestEnvelope request);

    @Headers({
            "Content-Type: application/soap+xml",
            "Accept-Charset: utf-8"
    })
    @POST("ws/CreateData")
    Call<CreateArrestResponseEnvelope> executeCreateArrest(@Header("Authorization") String auth, @Body CreateArrestRequestEnvelope request);

    @Headers({
            "Content-Type: application/soap+xml",
            "Accept-Charset: utf-8"
    })
    @POST("ws/CreateData")
    Call<CreateArrestWithOrganizationChangeResponseEnvelope> executeCreateArrestWithOrganizationChange(@Header("Authorization") String auth, @Body CreateArrestWithOrganizationChangeRequestEnvelope request);

    @Headers({
            "Content-Type: application/soap+xml",
            "Accept-Charset: utf-8"
    })
    @POST("ws/CreateData")
    Call<CreateUncheckArrestResponseEnvelope> executeCreateUncheckArrest(@Header("Authorization") String auth, @Body CreateUncheckArrestRequestEnvelope request);

    @Headers({
            "Content-Type: application/soap+xml",
            "Accept-Charset: utf-8"
    })
    @POST("ws/CreateData")
    Call<CreateReleaseToAnotherParkingResponseEnvelope> executeCreateReleaseToAnotherParking(@Header("Authorization") String auth, @Body CreateReleaseToAnotherParkingRequestEnvelope request);

    @Headers({
            "Content-Type: application/soap+xml",
            "Accept-Charset: utf-8"
    })
    @POST("ws/CreateData")
    Call<CreateTakeFromAnotherParkingResponseEnvelope> executeCreateTakeFromAnotherParking(@Header("Authorization") String auth, @Body CreateTakeFromAnotherParkingRequestEnvelope request);

    @Headers({
            "Content-Type: application/soap+xml",
            "Accept-Charset: utf-8"
    })
    @POST("ws/CreateData")
    Call<CreateReleaseByDebtActResponseEnvelope> executeCreateReleaseByDebtAct(@Header("Authorization") String auth, @Body CreateReleaseByDebtActRequestEnvelope request);

    @Headers({
            "Content-Type: application/soap+xml",
            "Accept-Charset: utf-8"
    })
    @POST("ws/CreateData")
    Call<CreatePolicemanResponseEnvelope> executeCreatePoliceman(@Header("Authorization") String auth, @Body CreatePolicemanRequestEnvelope request);


    @POST("ws/getdata")
    Call<GetDebtActDocumentResponseEnvelope> executeGetDebtActDocument(@Header("Authorization") String auth, @Body GetDebtActDocumentRequestEnvelope request);

    @POST("ws/getdata")
    Call<GetProtocolResponseEnvelope> executeGetProtocol(@Header("Authorization") String auth, @Body GetProtocolRequestEnvelope request);

    @POST("ws/getdata")
    Call<GetReceiptResponseEnvelope> executeGetReceipt(@Header("Authorization") String auth, @Body GetReceiptRequestEnvelope request);

    @POST("ws/getdata")
    Call<GetReleaseInformationResponseEnvelope> executeGetReleaseInformation(@Header("Authorization") String auth, @Body GetReleaseInformationRequestEnvelope request);

    @POST("ws/getdata")
    Call<GetPhotosResponseEnvelope> executeGetPhotos(@Header("Authorization") String auth, @Body GetPhotosRequestEnvelope request);

    @POST("ws/getdata")
    Call<GetOrganizationForArrestResponseEnvelope> executeGetOrganizationForArrest(@Header("Authorization") String auth, @Body GetOrganizationForArrestRequestEnvelope request);


    @POST("ws/getdata")
    Call<GetParkingsResponseEnvelope> executeGetParkings(@Header("Authorization") String auth, @Body GetParkingsRequestEnvelope request);

    @POST("ws/getdata")
    Call<GetOwnerDriverDataResponseEnvelope> executeGetOwnerDriverData(@Header("Authorization") String auth, @Body GetOwnerDriverDataRequestEnvelope request);

    @POST("ws/getdata")
    Call<GetCarListFromBaseResponseEnvelope> executeGetCarListFromBase(@Header("Authorization") String auth, @Body GetCarListFromBaseRequestEnvelope request);

    @POST("ws/getdata")
    Call<GetRanksResponseEnvelope> executeGetRanks(@Header("Authorization") String auth, @Body GetRanksRequestEnvelope request);

    @POST("ws/getdata")
    Call<GetPositionsResponseEnvelope> executeGetPositions(@Header("Authorization") String auth, @Body GetPositionsRequestEnvelope request);

    @POST("ws/getdata")
    Call<GetPoliceDepartmentResponseEnvelope> executeGetPoliceDepartment(@Header("Authorization") String auth, @Body GetPoliceDepartmentRequestEnvelope request);

    @Headers({
            "Content-Type: application/soap+xml",
            "Accept-Charset: utf-8"
    })
    @POST("ws/getData")
    Call<GetVoucherResponseEnvelope> executeGetVoucher(@Header("Authorization") String auth, @Body GetVoucherRequestEnvelope request);
}
