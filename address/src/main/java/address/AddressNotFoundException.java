package address;

class AddressNotFoundException extends RuntimeException {

    AddressNotFoundException(Long id) {
        super("Could not find Address " + id);
    }
}