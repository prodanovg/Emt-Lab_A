import React, {useState} from 'react';
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    FormControl,
    InputLabel, MenuItem, Select,
    TextField
} from "@mui/material";
import useAuthors from "../../../../hooks/useAuthors.js";

const EditBookDialog = ({open, onClose, book, onEdit}) => {
    const [formData, setFormData] = useState({
        "name": book.name,
        "category": book.category,
        "authorId": book.authorId,
        "availableCopies": book.availableCopies,
        "description": book.description,
    });
    const authors = useAuthors();


    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    };

    const handleSubmit = () => {
        onEdit(book.id, formData);
        setFormData(formData);
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Edit Book</DialogTitle>
            <DialogContent>
                <TextField
                    margin="dense"
                    label="Name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    fullWidth
                />
                <InputLabel id="category-label">Category</InputLabel>
                <FormControl fullWidth margin="dense">
                    <InputLabel id="category-label">Category</InputLabel>
                    <Select
                        labelId="category-label"
                        name="category"
                        value={formData.category}
                        onChange={handleChange}
                        label="Category"
                    >
                        <MenuItem value="NOVEL">Novel</MenuItem>
                        <MenuItem value="THRILER">Thriler</MenuItem>
                        <MenuItem value="HISTORY">History</MenuItem>
                        <MenuItem value="FANTASY">Fantasy</MenuItem>
                        <MenuItem value="BIOGRAPHY">Biography</MenuItem>
                        <MenuItem value="CLASSICS">Classics</MenuItem>
                        <MenuItem value="DRAMA">Drama</MenuItem>
                    </Select>
                </FormControl>
                <FormControl fullWidth margin="dense">
                    <InputLabel>Author</InputLabel>
                    <Select
                        name="authorId"
                        value={formData.authorId}
                        onChange={handleChange}
                        label="Author"
                        variant="outlined">
                        {authors.map((author) => (
                            <MenuItem key={author.id} value={author.id}>{author.name}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
                <TextField
                    margin="dense"
                    label="Available Copies"
                    name="availableCopies"
                    type="number"
                    value={formData.availableCopies}
                    onChange={handleChange}
                    fullWidth
                />
                <TextField
                    margin="dense"
                    label="Description"
                    name="description"
                    type="text"
                    value={formData.description}
                    onChange={handleChange}
                    fullWidth
                />

            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="warning">Edit</Button>
            </DialogActions>
        </Dialog>
    );
};

export default EditBookDialog;
