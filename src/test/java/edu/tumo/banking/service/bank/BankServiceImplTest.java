package edu.tumo.banking.service.bank;

import edu.tumo.banking.domain.bank.model.BankModel;
import edu.tumo.banking.exception.NotFoundValue;
import edu.tumo.banking.exception.ResourceNotFound;
import edu.tumo.banking.exception.ResourceNotValid;
import edu.tumo.banking.repository.bank.BankRepository;
import edu.tumo.banking.validation.BankValidation;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankServiceImplTest {

    @Mock
    private BankRepository bankRepository;

    @Autowired
    @InjectMocks
    private BankServiceImpl bankService;

    private BankModel bankModel;
    private BankModel updateBankModel;
    private List<BankModel> bankList;

    @BeforeEach
    void setUp() {
        bankList= new ArrayList<>();
        bankModel=new BankModel(1L,"bank_name","bank_address");
        updateBankModel=new BankModel(1L,"updated_bank","updated_address");
        bankList.add(bankModel);
    }

    @AfterEach
    public  void tearDown()
    {
        bankModel=null;
        bankList=null;
    }

    @Test
    @DisplayName("Add function returns added valid bank")
    public  void add_valid()
    {
        when(bankRepository.add(any())).thenReturn(bankModel);
        bankService.add(bankModel);
        verify(bankRepository).add(any());
    }

    @Test
    @DisplayName("Add function when bank is null")
    public void add_bank_null()
    {
        Assertions.assertFalse(BankValidation.validateBankModel(null));
        ResourceNotValid resourceNotValid=Assertions.assertThrows(ResourceNotValid.class,() -> bankService.add(null));
        Assertions.assertEquals(resourceNotValid.getMessage(),"Bank is not valid");
    }

    @Test
    @DisplayName("Add function when bankName is empty")
    public void add_bankName_empty()
    {
        BankModel emptyBankName=new BankModel("","notEmpty");
        Assertions.assertFalse(BankValidation.validateBankModel(emptyBankName));
        ResourceNotValid resourceNotValid=Assertions.assertThrows(ResourceNotValid.class,() -> bankService.add(emptyBankName));
        Assertions.assertEquals(resourceNotValid.getMessage(),"BankName is not valid");
    }

    @Test
    @DisplayName("Add function when address is empty")
    public void add_bankAdress_empty()
    {
        BankModel emptyBankAdress=new BankModel("notEmpty","");
        Assertions.assertFalse(BankValidation.validateBankModel(emptyBankAdress));
        ResourceNotValid resourceNotValid=Assertions.assertThrows(ResourceNotValid.class,() -> bankService.add(emptyBankAdress));
        Assertions.assertEquals(resourceNotValid.getMessage(),"BankName is not valid");
    }

    @Test
    @DisplayName("findAll return list Of all banks")
    public void findAll_list_of_all_banks() {
        when(bankRepository.findAll()).thenReturn(bankList);
        List<BankModel> bankList = bankService.findAll();
        Assertions.assertEquals(bankList, bankList);
        verify(bankRepository).findAll();
    }

    @Test
    @DisplayName("findById return bank of valid id")
    public void findById() {
        when(bankRepository.findById(1L)).thenReturn(Optional.of(bankModel));
        Assertions.assertEquals(bankService.findById(bankModel.getBankID()),bankModel);
    }

    @Test
    @DisplayName("findById throws exception ResourceNot Found")
    public void findById_throw_resourceNotFound()
    {
        when(bankRepository.findById(2L)).thenReturn(Optional.empty());
        NotFoundValue notFoundValue= Assertions.assertThrows(NotFoundValue.class,()->bankService.findById(2L));
        Assertions.assertEquals(notFoundValue.getMessage(),"The bank id is not found");
    }

    @Test
    @DisplayName("update returns updated valid bank")
    public void update_valid_bank() {
        when(bankRepository.update(any())).thenReturn(Optional.ofNullable(updateBankModel));
        when(bankRepository.findById(bankModel.getBankID())).thenReturn(Optional.ofNullable(bankModel));
        bankService.update(bankModel);
        verify(bankRepository).update(any());
    }

    @Test
    @DisplayName("update when bank is null")
    public void update_when_bank_is_null() {
        Assertions.assertFalse(BankValidation.validateBankModel(null));
        ResourceNotValid resourceNotValid = Assertions.assertThrows(ResourceNotValid.class, () -> bankService.update(null));
        Assertions.assertEquals(resourceNotValid.getMessage(), "The bank is not valid");
    }

    @Test
    @DisplayName("update when bank name is empty")
    public void update_when_bankName_is_empty() {
        BankModel emptyBankModel=new BankModel(1L,"","notEmpty");
        Assertions.assertFalse(BankValidation.validateBankModel(emptyBankModel));
        ResourceNotValid resourceNotValid=Assertions.assertThrows(ResourceNotValid.class,()->bankService.update(emptyBankModel));
        Assertions.assertEquals(resourceNotValid.getMessage(),"The bankName is empty");
    }

    @Test
    @DisplayName("update when bank address is empty")
    public void update_when_bankAddress_is_empty() {
        BankModel emptyBankAddress=new BankModel(1L,"notEmpty","");
        Assertions.assertFalse(BankValidation.validateBankModel(emptyBankAddress));
        ResourceNotValid notValid=Assertions.assertThrows(ResourceNotValid.class,()->bankService.update(emptyBankAddress));
        Assertions.assertEquals(notValid.getMessage(), " For update the bankAddress is empty");
    }

    @Test
    @DisplayName("update when bank's id is null")
    public void update_when_bankId_null() {
        BankModel nullId=new BankModel(null ,"notEmpty", "notEmpty");
        Assertions.assertNull(nullId.getBankID());
        ResourceNotValid notValid=Assertions.assertThrows(ResourceNotValid.class,()-> bankService.update(nullId));
        Assertions.assertEquals(notValid.getMessage(),"For update the bankId is null");
    }

    @Test
    @DisplayName("update when bankId is negative")
    public void update_bankId_is_negative() {
        BankModel negativeBankModel=new BankModel(-1L, "notEmpty", "notEmpty");
        Assertions.assertTrue(negativeBankModel.getBankID()<=0);
        ResourceNotValid notValid=Assertions.assertThrows(ResourceNotValid.class,()->bankService.update(negativeBankModel));
        Assertions.assertEquals(notValid.getMessage(),"For update bankId is negative");
    }

    @Test
    @DisplayName("update Throws NotFoundValue")
    public void updateUser_throws_notFoundValue() {
        when(bankRepository.findById(2L)).thenReturn(Optional.empty());
        NotFoundValue notFound=Assertions.assertThrows(NotFoundValue.class,()-> bankService.deleteBankModelBy(2L));
        Assertions.assertEquals(notFound.getMessage(),"The user id is not found for update ");
    }

    @Test
    @DisplayName("deleteBankModelById Throws NotFoundException As User Is Not Found")
    public void deleteBankModelById_throws_notFoundValue() {
        when(bankRepository.findById(2L)).thenReturn(Optional.empty());
        NotFoundValue notFoundValue=Assertions.assertThrows(NotFoundValue.class,()->bankService.deleteBankModelBy(2L));
        Assertions.assertEquals(notFoundValue.getMessage(),"The user with id is not found for delete");
    }

}